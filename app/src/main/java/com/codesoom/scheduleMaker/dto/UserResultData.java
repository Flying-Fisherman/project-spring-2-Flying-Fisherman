package com.codesoom.scheduleMaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 생성, 변경된 회원정보 결과 DTO
 */
@Getter
@Builder
@AllArgsConstructor
public class UserResultData {

    /**
     * 생성, 변경된 회원정보 ID
     */
    private String id;

    /**
     * 생성, 변경된 회원 전화번호
     */
    private String phone;

    /**
     * 생성, 변경된 회원 이름
     */
    private String name;

    /**
     * 생성, 변경된 회원 이메일
     */
    private String email;
}
