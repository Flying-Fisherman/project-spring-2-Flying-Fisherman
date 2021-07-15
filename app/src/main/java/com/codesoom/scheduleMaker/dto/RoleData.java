package com.codesoom.scheduleMaker.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


/**
 * Role DTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleData {

    /**
     * Role 소유 회원 UID
     */
    @NotBlank
    @Mapping("userUid")
    private Long userUid;

    /**
     * Role 이름
     */
    @NotBlank
    @Mapping("roleName")
    private String roleName;
}
