package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.errors.LoginFailException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new LoginFailException());

        return null;
    }
}
