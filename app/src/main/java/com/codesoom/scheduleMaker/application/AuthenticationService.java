package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Role;
import com.codesoom.scheduleMaker.domain.RoleRepository;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.errors.LoginFailException;
import com.codesoom.scheduleMaker.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
    }

    public String login(String userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new LoginFailException());

        if(!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailException();
        }

        return jwtUtil.encode(user.getUid());
    }

    public Long parseToken(String accessToken) {
        Claims claims = jwtUtil.decode(accessToken);
        return claims.get("userUid", Long.class);
    }

    public List<Role> roles(Long userUid) {
        return roleRepository.findAllByUserUid(userUid);
    }
}
