package com.codesoom.scheduleMaker.domain;


import java.util.List;
import java.util.Optional;

/**
 * Schedule 레포지토리
 */
public interface ScheduleRepository {
    /**
     * Schedule 목록을 반환
     *
     * @return Schedule 목록
     */
    List<Schedule> findAll();

    /**
     * Schedule 정보 유무 객체를 반환
     *
     * @param id Scheduel 고유 식별자
     * @return Schedule 정보 유무 객체
     */
    Optional<Schedule> findById(Long id);

    Optional<Schedule> findByUserId(Long userId);

    /**
     * Schedule 정보를 저장
     *
     * @param schedule 저장 할 Schedule 정보
     * @return 저장된 Schedule 정보
     */
    Schedule save(Schedule schedule);

    /**
     * Schedule 정보를 삭제
     *
     * @param schedule 삭제 할 Schedule 정보
     */
    void delete(Schedule schedule);
}
