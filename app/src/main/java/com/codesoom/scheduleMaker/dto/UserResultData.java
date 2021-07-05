package com.codesoom.scheduleMaker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResultData {

    private String id;

    private String phone;

    private String name;

    private String email;
}
