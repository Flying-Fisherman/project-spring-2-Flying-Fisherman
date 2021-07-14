package com.codesoom.scheduleMaker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User의 권한
 */
@Entity
@NoArgsConstructor
public class Role {

    /**
     * 권한의 고유 ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 권한을 가진 User ID
     */
    private Long userUid;

    /**
     * 권한의 이름
     */
    @Getter
    private String roleName;

    public Role(Long userUid, String roleName) {
        this.userUid = userUid;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this(null, roleName);
    }
}
