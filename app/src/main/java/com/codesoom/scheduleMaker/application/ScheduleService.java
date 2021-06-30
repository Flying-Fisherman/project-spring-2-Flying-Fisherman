package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.ScheduleRepository;
import com.codesoom.scheduleMaker.dto.ScheduleData;
import com.codesoom.scheduleMaker.errors.ScheduleNotFoundException;
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

    public Schedule getSchedule(Long id) {
        return findSchedule(id);
    }

    public Schedule createSchedule(ScheduleData scheduleData) {
        Schedule schedule = mapper.map(scheduleData, Schedule.class);
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Long id, ScheduleData scheduleData) {
        Schedule schedule = findSchedule(id);

        schedule.updataData(mapper.map(scheduleData, Schedule.class));

        return schedule;
    }

    public Schedule deleteSchdule(Long id) {
        Schedule schedule = findSchedule(id);

        scheduleRepository.delete(schedule);

        return schedule;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }
}
