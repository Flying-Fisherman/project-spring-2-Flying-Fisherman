package com.codesoom.scheduleMaker.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 회원정보 등록 DTO
 */
@Getter
@Builder
@AllArgsConstructor
public class UserRegistrationData {

    /**
     * 등록할 회원의 ID
     */
    @NotBlank
    @Size(min = 4)
    @Mapping("id")
    private String id;

    /**
     * 등록할 회원의 비밀번호
     */
    @NotBlank
    @Pattern(regexp = "[a-zA-z0-9]{6,20}", message = "비밀번호는 영어와 숫자로 포함해서 6~20자리 이내로 입력해주세요")
    @Mapping("password")
    private String password;

    /**
     * 등록할 회원의 전화번호
     */
    @NotBlank
    @Size(min = 12, max = 13)
    @Mapping("phone")
    private String phone;

    /**
     * 등록할 회원의 이름
     */
    @NotBlank
    @Mapping("name")
    private String name;

    /**
     * 등록할 회원의 이메일
     */
    @Email
    @Mapping("email")
    private String email;
}
