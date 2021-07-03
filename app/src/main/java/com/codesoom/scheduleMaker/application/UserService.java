package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.UserRegistrationData;
import com.codesoom.scheduleMaker.errors.UserDataDuplicationException;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final Mapper mapper;
    private final UserRepository userRepository;

    public UserService(Mapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationData registrationData) {
        if(!checkDuplication(registrationData)) {
            throw new UserDataDuplicationException();
        }

        User user = userRepository.save(
                mapper.map(registrationData, User.class));

        return user;
    }

    // ToDo : 수정과 삭제, 암호화, 권한...

    private boolean checkDuplication(UserRegistrationData registrationData) {
        boolean check1 = userRepository.existsByEmail(registrationData.getEmail());
        boolean check2 = userRepository.existsById(registrationData.getId());
        boolean check3 = userRepository.existsByPhone(registrationData.getPhone());

        return !check1 || !check2 || !check3;
    }
}
