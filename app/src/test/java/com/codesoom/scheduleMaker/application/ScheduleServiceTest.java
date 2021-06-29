package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.ScheduleRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ScheduleServiceTest {
    private ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository =
            mock(ScheduleRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        scheduleService = new ScheduleService(mapper, scheduleRepository);
    }

    @Test
    void getAllSchedulesWithNothing() {
        given(scheduleRepository.findAll()).willReturn(List.of());

        assertThat(scheduleService.getAllSchedules()).isEmpty();
    }
}