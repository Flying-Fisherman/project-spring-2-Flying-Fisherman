package com.codesoom.scheduleMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User 정보
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * User 고유 식별자
     */
    @Id
    @GeneratedValue
    private Long uid;

    /**
     * User ID
     */
    @Unique
    private String id;

    /**
     * User 비밀번호
     */
    private String password;

    /**
     * User 전화번호
     */
    @Unique
    private String phone;

    /**
     * User 이름
     */
    private String name;

    /**
     * User 이메일
     */
    @Unique
    private String email;

    public void changePassword(String password,
                               PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void changeWith(User source) {
        phone = source.phone;
        name = source.name;
        email = source.email;
    }
}
