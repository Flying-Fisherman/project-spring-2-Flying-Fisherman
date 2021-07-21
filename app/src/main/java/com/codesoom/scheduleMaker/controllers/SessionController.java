package com.codesoom.scheduleMaker.controllers;

import com.codesoom.scheduleMaker.application.AuthenticationService;
import com.codesoom.scheduleMaker.dto.SessionResponseData;
import com.codesoom.scheduleMaker.dto.SessionRequestData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증에 대한 HTTP의 요청을 처리합니다.
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    private final AuthenticationService authenticationService;

    public SessionController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * 로그인을 시도합니다.
     *
     * @param sessionRequestData 로그인 하려는 유저의 ID와 Password
     * @return 로그인 하는 유저의 토큰
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseData login(
            @RequestBody SessionRequestData sessionRequestData
    ) {
        String userId = sessionRequestData.getId();
        String password = sessionRequestData.getPassword();

        String accessToken = authenticationService.login(userId, password);

        return SessionResponseData.builder()
                .accessToken(accessToken)
                .build();
    }
}
