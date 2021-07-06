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

/**
 * 회원정보의 CRUD에 대한 HTTP 요청을 처리합니다.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 새로운 회원정보를 생성합니다.
     *
     * @param registrationData 새로 등록하려는 회원정보
     * @return 등록된 회원정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // ToDo : BindingResult를 사용한 상세한 Validation
    UserResultData create (@RequestBody @Valid UserRegistrationData registrationData) {
        User user = userService.registerUser(registrationData);

        return getUserResultData(user);
    }

    /**
     * 회원을 확인하고, 회원 정보를 수정합니다.
     *
     * @param uid 수정하려는 회원정보 UID
     * @param modificationData 수정하려는 회원정보
     * @param authentication 수정을 시도하는 회원의 인증 토큰
     * @return 수정된 회원정보
     */
    @PatchMapping("{id}")
    // ToDo : BindingResult를 사용한 상세한 Validation
    UserResultData update(
            @PathVariable Long uid,
            @RequestBody @Valid UserModificationData modificationData,
            UserAuthentication authentication
    ) {
        Long accessUid = authentication.getUserUid();
        User user = userService.updateUser(uid, accessUid, modificationData);

        return getUserResultData(user);
    }

    /**
     * 회원정보를 삭제합니다.
     *
     * @param uid 삭제하려는 회원정보
     */
    @DeleteMapping("{id}")
    void delete(@PathVariable Long uid) {
        userService.deleteUser(uid);
    }

    /**
     * 회원정보의 생성, 변경의 결과값을 구축합니다.
     *
     * @param user 생성, 변경된 회원정보
     * @return 생성, 변경된 회원정보의 결과값
     */
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
