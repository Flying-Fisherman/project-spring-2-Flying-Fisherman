package com.codesoom.scheduleMaker.domain;

import java.util.Optional;

/**
 * 회원정보 레포지토리
 */
public interface UserRepository {

    /**
     * 회원정보를 저장
     *
     * @param user 저장 할 회원정보
     * @return 저장된 회원정보
     */
    User save(User user);

    /**
     * 이메일 중복 검사
     *
     * @param email 중복 검사 할 이메일
     * @return 중복 검사 결과
     */
    boolean existsByEmail(String email);

    /**
     * ID 중복 검사
     *
     * @param id 중복 검사 할 ID
     * @return 중복 검사 결과
     */
    boolean existsById(String id);

    /**
     * 전화번호 중복 검사
     *
     * @param phone 중복 검사 할 전화번호
     * @return 중복 검사 결과
     */
    boolean existsByPhone(String phone);

    /**
     * ID를 통한 회원정보 검색
     *
     * @param id 검색 할 회원정보 ID
     * @return 회원정보 유무 객체
     */
    Optional<User> findById(String id);

    /**
     * UID를 통한 회원정보 검색
     *
     * @param uid 검색 할 회원정보 UID
     * @return 회원정보 유무 객체
     */
    Optional<User> findByUid(Long uid);

    /**
     * 회원정보 삭제
     *
     * @param user 삭제할 회원정보
     */
    void delete(User user);
}
