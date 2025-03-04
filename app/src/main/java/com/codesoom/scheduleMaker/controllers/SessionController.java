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

@RestController
@RequestMapping("/session")
public class SessionController {
    private final AuthenticationService authenticationService;

    public SessionController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseData login(
            @RequestBody SessionRequestData sessionrequestData
    ) {
        String userId = sessionrequestData.getUserId();
        String password = sessionrequestData.getPassword();

        String accessToken = authenticationService.login(userId, password);

        return SessionResponseData.builder()
                .accessToken(accessToken)
                .build();
    }
}
