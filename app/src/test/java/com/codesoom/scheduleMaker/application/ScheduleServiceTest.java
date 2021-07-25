package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.ScheduleRepository;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.ScheduleData;
import com.codesoom.scheduleMaker.errors.ScheduleNotFoundException;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ScheduleServiceTest {
    private ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository =
            mock(ScheduleRepository.class);
    private final UserRepository userRepository =
            mock(UserRepository.class);

    private static final Long EXISTED_ID = 1L;
    private static final Long NOT_EXISTED_ID = 100L;

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        scheduleService = new ScheduleService(mapper, scheduleRepository, userRepository);

        Schedule schedule = Schedule.builder()
                .id(1L)
                .title("ToDo")
                .content("Testing ToDo")
                .build();

        given(scheduleRepository.findAll()).willReturn(List.of(schedule));

        given(scheduleRepository.findById(EXISTED_ID)).willReturn(Optional.of(schedule));

        given(scheduleRepository.save(any(Schedule.class))).will(invocation -> {
            Schedule source = invocation.getArgument(0);
            return Schedule.builder()
                    .id(source.getId())
                    .title(source.getTitle())
                    .content(source.getContent())
                    .build();
        });
    }

    @Test
    void getAllSchedulesWithNothing() {
        given(scheduleRepository.findAll()).willReturn(List.of());

        assertThat(scheduleService.getAllSchedules()).isEmpty();
    }

    @Test
    void getAllScheduleWithSomething() {
        List<Schedule> schedules = scheduleService.getAllSchedules();

        assertThat(schedules).isNotEmpty();
        assertThat(schedules.get(0).getId())
                .isEqualTo(EXISTED_ID);
    }

    @Test
    void getScheduleWithExistedId() {
        Schedule schedule = scheduleService.getSchedule(EXISTED_ID);

        assertThat(schedule).isNotNull();
        assertThat(schedule.getId()).isEqualTo(EXISTED_ID);
    }

    @Test
    void getScheduleWithNotExistedId() {
        assertThatThrownBy(() -> scheduleService.getSchedule(NOT_EXISTED_ID))
                .isInstanceOf(ScheduleNotFoundException.class);
    }

    @Test
    void createSchedule() {
        ScheduleData scheduleData = ScheduleData.builder()
                .title("ToDo")
                .content("Testing ToDo")
                .build();

        User user = new User();

        Schedule schedule = scheduleService.createSchedule(scheduleData, user);

        verify(scheduleRepository).save(any(Schedule.class));

        assertThat(schedule.getTitle())
                .isEqualTo("ToDo");
        assertThat(schedule.getContent())
                .isEqualTo("Testing ToDo");
    }

    @Test
    void updateSchedule() {
        ScheduleData scheduleData = ScheduleData.builder()
                .title("Another ToDo")
                .content("Testing Another ToDo")
                .build();

        Schedule schedule = scheduleService.updateSchedule(EXISTED_ID, scheduleData);

        assertThat(schedule.getTitle()).isEqualTo("Another ToDo");
        assertThat(schedule.getContent()).isEqualTo("Testing Another ToDo");
    }

    @Test
    void deleteSchedule() {
        scheduleService.deleteSchdule(EXISTED_ID);

        verify(scheduleRepository).delete(any(Schedule.class));
    }
}
