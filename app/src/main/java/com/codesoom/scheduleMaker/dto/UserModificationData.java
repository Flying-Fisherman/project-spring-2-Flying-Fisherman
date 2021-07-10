package com.codesoom.scheduleMaker.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * 회원정보 갱신 DTD
 */
@Getter
@Builder
@AllArgsConstructor
public class UserModificationData {

    /**
     * 변경할 회원의 비밀번호
     */
    @NotBlank
    @Mapping("password")
    private String password;

    /**
     * 변경할 회원의 전화번호
     */
    @NotBlank
    @Mapping("phone")
    private String phone;

    /**
     * 변경할 회원의 이름
     */
    @NotBlank
    @Mapping("name")
    private String name;

    /**
     * 변경할 회원의 이메일
     */
    @NotBlank
    @Mapping("email")
    private String email;
}
