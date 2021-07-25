package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.ScheduleRepository;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.ScheduleData;
import com.codesoom.scheduleMaker.errors.ScheduleNotFoundException;
import com.codesoom.scheduleMaker.errors.UserNotFoundException;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Schedule에 대한 CRUD 처리를 담당합니다.
 */
@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final Mapper mapper;
    private final UserRepository userRepository;

    public ScheduleService(Mapper dozerMapper, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.mapper = dozerMapper;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    /**
     * Schedule 목록을 리턴합니다.
     *
     * @return Schedule 목록
     */
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    /**
     * 고유 식별자로 Schedule을 검색해 리턴합니다.
     *
     * @param id Schedule 식별자
     * @return 검색된 Schedule
     */
    public Schedule getSchedule(Long id) {
        return findSchedule(id);
    }

    /**
     * Schedule을 생성합니다.
     *
     * @param scheduleData 생성하려는 Schedule 정보
     * @param user
     * @return 생성된 Schedule
     */
    public Schedule createSchedule(ScheduleData scheduleData, User user) {
        System.out.println(user.getId());
        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(1L));
        System.out.println(user.getUid());
        scheduleData.setUserId(user.getUid());
        Schedule schedule = mapper.map(scheduleData, Schedule.class);
        return scheduleRepository.save(schedule);
    }

    /**
     * Schedule의 정보를 수정합니다.
     *
     * @param id Schedule 식별자
     * @param scheduleData 수정하려는 Schedule 정보
     * @return 수정된 Schedule
     */
    public Schedule updateSchedule(Long id, ScheduleData scheduleData) {
        Schedule schedule = findSchedule(id);

        schedule.updataData(mapper.map(scheduleData, Schedule.class));

        return schedule;
    }

    /**
     * Schedule을 삭제합니다.
     *
     * @param id Schedule 식별자
     * @return 삭제된 Schedule
     */
    public Schedule deleteSchdule(Long id) {
        Schedule schedule = findSchedule(id);

        scheduleRepository.delete(schedule);

        return schedule;
    }

    /**
     * 식별자를 이용해 Schedule을 검색 후 리턴합니다.
     *
     * @param id Schedule 식별자
     * @return 검색된 Schedule
     * @throws ScheduleNotFoundException 검색 조건에 맞는 Schedule이 없을 경우
     */
    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }
}
