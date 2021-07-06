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

    /**
     * 회원정보의 비밀변호를 변경, 암호화합니다.
     *
     * @param password 변경 할 회원정보의 비밀번호
     * @param passwordEncoder 비밀번호 암호화 모듈
     */
    public void changePassword(String password,
                               PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    /**
     * 회원정보의 정보를 변경합니다.
     *
     * @param source 변경 할 회원정보
     */
    public void changeWith(User source) {
        phone = source.phone;
        name = source.name;
        email = source.email;
    }

    /**
     * 로그인 시 올바른 비밀번호인지 판별합니다.
     *
     * @param password 입력받은 비밀번호
     * @param passwordEncoder 비밀번호 암호화 모듈
     * @return 비밀번호 판별 결과
     */
    public boolean authenticate(String password,
                                PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
