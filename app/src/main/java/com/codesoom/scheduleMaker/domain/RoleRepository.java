package com.codesoom.scheduleMaker.domain;

import java.util.List;

public interface RoleRepository {
    /**
     * 권한 목록을 반환
     *
     * @param userUid 회원의 고유 식별자
     * @return 지정된 사용자의 권한 목록
     */
    List<Role> findAllByUserUid(Long userUid);

    /**
     * 권한 정보를 저장
     *
     * @param role 저장할 권한 정보
     * @return 저장된 권한 정보
     */
    Role save(Role role);
}
