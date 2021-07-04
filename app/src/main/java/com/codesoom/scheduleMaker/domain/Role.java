package com.codesoom.scheduleMaker.domain;

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
    private String name;

    public Role(Long userUid, String name) {
        this.userUid = userUid;
        this.name = name;
    }

    public Role(String name) {
        this(null, name);
    }
}
