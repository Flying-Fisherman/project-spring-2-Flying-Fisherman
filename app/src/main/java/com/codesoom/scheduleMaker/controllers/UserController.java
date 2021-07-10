package com.codesoom.scheduleMaker.controllers;

import com.codesoom.scheduleMaker.application.UserService;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.dto.UserModificationData;
import com.codesoom.scheduleMaker.dto.UserRegistrationData;
import com.codesoom.scheduleMaker.dto.UserResultData;
import com.codesoom.scheduleMaker.security.UserAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResultData create (@RequestBody @Valid UserRegistrationData registrationData) {
        User user = userService.registerUser(registrationData);

        return getUserResultData(user);
    }

    @PatchMapping("{id}")
    UserResultData update(
            @PathVariable Long uid,
            @RequestBody @Valid UserModificationData modificationData,
            UserAuthentication authentication
    ) {
        Long accessUid = authentication.getUserId();
        User user = userService.updateUser(uid, accessUid, modificationData);

        return getUserResultData(user);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long uid) {
        userService.deleteUser(uid);
    }

    private UserResultData getUserResultData(User user) {
        if (user == null) {
            return null;
        }

        return UserResultData.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }
}
