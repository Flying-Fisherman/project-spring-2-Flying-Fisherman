package com.codesoom.scheduleMaker.controllers;

import com.codesoom.scheduleMaker.application.ScheduleService;
import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.dto.ScheduleData;
import com.codesoom.scheduleMaker.errors.ScheduleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureRestDocs
class ScheduleControllerTest {

    private static final Long REGISTERED_ID = 1L;
    private static final Long NOT_REGISTERED_ID = 100L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        Schedule schedule = Schedule.builder()
                .id(REGISTERED_ID)
                .title("Test")
                .content("Testing")
                .build();

        User user = new User();

        given(scheduleService.getAllSchedules())
                .willReturn(List.of(schedule));

        given(scheduleService.getSchedule(REGISTERED_ID))
                .willReturn(schedule);

        given(scheduleService.getSchedule(NOT_REGISTERED_ID))
                .willThrow(new ScheduleNotFoundException(NOT_REGISTERED_ID));

        given(scheduleService.createSchedule(any(ScheduleData.class), user))
                .willReturn(schedule);

        given(scheduleService.updateSchedule(eq(REGISTERED_ID), any(ScheduleData.class)))
                .will(invocation -> {
                    Long id = invocation.getArgument(0);
                    ScheduleData scheduleData = invocation.getArgument(1);
                    return Schedule.builder()
                            .id(id)
                            .title(scheduleData.getTitle())
                            .content(scheduleData.getContent())
                            .build();
                });

        given(scheduleService.updateSchedule(eq(NOT_REGISTERED_ID), any(ScheduleData.class)))
                .willThrow(new ScheduleNotFoundException(NOT_REGISTERED_ID));

        given(scheduleService.deleteSchdule(NOT_REGISTERED_ID))
                .willThrow(new ScheduleNotFoundException(NOT_REGISTERED_ID));
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(
                get("/schedule")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Testing")))
                .andDo(document("get-all-schedules"));
    }

    @Test
    void getDetailWithExistedSchedule() throws Exception {
        mockMvc.perform(
                get("/schedule/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Testing")))
                .andDo(document("get-schedule"));
    }

    @Test
    void getDetailWithNotExistedSchedule() throws Exception {
        mockMvc.perform(
                get("/schedule/100")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isNotFound())
                .andDo(document("get-schedule-not-exist"));
    }

    @Test
    void createWithValidAttributes() throws Exception {
        mockMvc.perform(
                post("/schedule")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test\",\"content\":\"Testing\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Testing")))
                .andDo(document("create-schedule"));
    }

    @Test
    void createWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                post("/schedule")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\",\"content\":\"\"}")
        )
                .andExpect(status().isBadRequest())
                .andDo(document("create-schedule-invalid"));
    }

    @Test
    void updateWithExistedSchedule() throws Exception {
        mockMvc.perform(
                patch("/schedule/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Test\",\"content\":\"Testing Other\"}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Testing Other")))
                .andDo(document("update-schedule"));
    }

    @Test
    void updateWithNotExistedSchedule() throws Exception {
        mockMvc.perform(
                patch("/schedule/100")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Test\",\"content\":\"Testing Other\"}")
        )
                .andExpect(status().isNotFound())
                .andDo(document("update-schedule-not-existed"));
    }

    @Test
    void deleteWithExistedSchedule() throws Exception {
        mockMvc.perform(
                delete("/schedule/1")
        )
                .andExpect(status().isOk())
                .andDo(document("delete-schedule"));

        verify(scheduleService).deleteSchdule(REGISTERED_ID);
    }

    @Test
    void deleteWithNotExistedSchedule() throws Exception {
        mockMvc.perform(
                delete("/schedule/100")
        )
                .andExpect(status().isNotFound())
                .andDo(document("delete-schedule-not-existed"));

        verify(scheduleService).deleteSchdule(NOT_REGISTERED_ID);
    }
}
