package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.ScheduleRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final Mapper mapper;

    public ScheduleService(Mapper dozerMapper, ScheduleRepository scheduleRepository) {
        this.mapper = dozerMapper;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getSchedule() {
        return null;
    }

    public Schedule createSchedule() {
        return null;
    }

    public Schedule updateSchedule() {
        return null;
    }

    public Schedule deleteSchdule() {
        return null;
    }
}
