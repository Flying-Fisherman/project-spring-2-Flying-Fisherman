package com.codesoom.scheduleMaker.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class UserRegistrationData {

    @NotBlank
    @Size(min = 4)
    @Mapping("id")
    private String id;

    @NotBlank
    @Size(min = 6, max = 32)
    @Mapping("password")
    private String password;

    @NotBlank
    @Size(min = 12, max = 13)
    @Mapping("phone")
    private String phone;

    @NotBlank
    @Mapping("name")
    private String name;

    @Mapping("email")
    private String email;
}
