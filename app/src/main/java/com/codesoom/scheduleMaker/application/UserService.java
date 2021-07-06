package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.UserModificationData;
import com.codesoom.scheduleMaker.dto.UserRegistrationData;
import com.codesoom.scheduleMaker.errors.UserDataDuplicationException;
import com.codesoom.scheduleMaker.errors.UserNotFoundException;
import com.github.dozermapper.core.Mapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

/**
 * 회원정보에 대한 CRUD 처리를 담당합니다.
 */
@Service
@Transactional
public class UserService {
    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(Mapper mapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원정보를 등록합니다.
     *
     * @param registrationData 등록 할 회원정보
     * @return 등록된 회원정보
     */
    public User registerUser(UserRegistrationData registrationData) {
        if(!checkDuplication(registrationData)) {
            throw new UserDataDuplicationException();
        }

        User user = userRepository.save(
                mapper.map(registrationData, User.class));

        user.changePassword(registrationData.getPassword(), passwordEncoder);

        return user;
    }

    /**
     * 회원정보를 변경합니다.
     *
     * @param updateUid 변경 할 회원정보 UID
     * @param accessUid 현재 접근중인 회원 UID
     * @param userModificationData 변경 할 회원정보
     * @return 변경된 회원정보
     * @throws AccessDeniedException 회원정보를 변경 할 권한이 없는 경우
     */
    public User updateUser(Long updateUid, Long accessUid,
                           UserModificationData userModificationData) {
        if(!updateUid.equals(accessUid)) {
            throw new AccessDeniedException("Access denied");
        }

        User user = findUser(updateUid);

        User source = mapper.map(userModificationData, User.class);
        user.changeWith(source);
        user.changePassword(source.getPassword(), passwordEncoder);

        return user;
    }

    /**
     * 회원정보를 삭제합니다.
     *
     * @param uid 삭제 할 회원정보 UID
     * @return 삭제된 회원정보
     */
    public User deleteUser(Long uid) {
        User user = findUser(uid);
        userRepository.delete(user);

        return user;
    }

    /**
     * UID를 통해 회원정보를 검색합니다.
     *
     * @param uid 검색 할 회원정보 UID
     * @return 검색된 회원정보 UID
     */
    private User findUser(Long uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));
    }

    /**
     * 회원정보 등록 시 입력 정보에 대한 중복성을 판별합니다.
     *
     * @param registrationData 중복성을 판별해야 되는 회원정보
     * @return 중복 여부 결과
     */
    private boolean checkDuplication(UserRegistrationData registrationData) {
        boolean check1 = userRepository.existsByEmail(registrationData.getEmail());
        boolean check2 = userRepository.existsById(registrationData.getId());
        boolean check3 = userRepository.existsByPhone(registrationData.getPhone());

        return !check1 && !check2 && !check3;
    }
}
