package com.codesoom.scheduleMaker.infra;

import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.ScheduleRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JpaScheduleRepository
        extends ScheduleRepository, CrudRepository<Schedule, Long> {
    List<Schedule> findAll();

    Optional<Schedule> findById(Long id);

    Schedule save(Schedule schedule);

    void delete(Schedule schedule);

    Optional<Schedule> findByUserId(Long userId);
}
