package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Role;
import com.codesoom.scheduleMaker.domain.RoleRepository;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.RoleData;
import com.codesoom.scheduleMaker.errors.LoginFailException;
import com.codesoom.scheduleMaker.utils.JwtUtil;
import com.github.dozermapper.core.Mapper;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 인증 시 처리를 담당합니다.
 */
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;
    private final Mapper mapper;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RoleRepository roleRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    /**
     * 로그인합니다.
     *
     * @param userId 로그인 하려는 회원의 ID
     * @param password 로그인 하려는 회원의 비밀번호
     * @return 회원의 인증 토큰
     * @throws LoginFailException 사용자 인증 정보를 찾을 수 없는 경우
     */
    public String login(String userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new LoginFailException());

        if(!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailException();
        }

        return jwtUtil.encode(user.getUid());
    }

    /**
     * 토큰을 파싱하여 회원의 UID를 반환합니다.
     *
     * @param accessToken 회원의 토큰
     * @return 토큰에 해당하는 회원의 UID
     */
    public Long parseToken(String accessToken) {
        Claims claims = jwtUtil.decode(accessToken);
        return claims.get("userUid", Long.class);
    }

    public List<Role> roles(Long userUid) {
        return roleRepository.findAllByUserUid(userUid);
    }

    /**
     * 회원정보 생성 시 기본 권한을 설정합니다.
     *
     * @param user 권한을 받는 User
     * @return 권한 정보
     */
    public Role setBasicRole(User user) {
        RoleData roleData = RoleData.builder()
                .userUid(user.getUid())
                .roleName("USER")
                .build();

        return roleRepository.save(mapper.map(roleData, Role.class));
    }
}
