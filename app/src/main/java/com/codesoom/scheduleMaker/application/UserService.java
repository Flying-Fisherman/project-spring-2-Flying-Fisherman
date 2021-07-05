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

    public User registerUser(UserRegistrationData registrationData) {
        if(!checkDuplication(registrationData)) {
            throw new UserDataDuplicationException();
        }

        User user = userRepository.save(
                mapper.map(registrationData, User.class));

        user.changePassword(registrationData.getPassword(), passwordEncoder);

        return user;
    }

    // ToDo : 수정과 삭제, 암호화, 권한...
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

    public User deleteUser(Long uid) {
        User user = findUser(uid);
        userRepository.delete(user);

        return user;
    }

    private User findUser(Long uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));
    }

    private boolean checkDuplication(UserRegistrationData registrationData) {
        boolean check1 = userRepository.existsByEmail(registrationData.getEmail());
        boolean check2 = userRepository.existsById(registrationData.getId());
        boolean check3 = userRepository.existsByPhone(registrationData.getPhone());

        return !check1 && !check2 && !check3;
    }
}
