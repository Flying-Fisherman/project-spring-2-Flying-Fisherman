package com.codesoom.scheduleMaker.controllers;

import com.codesoom.scheduleMaker.application.ScheduleService;
import com.codesoom.scheduleMaker.domain.Schedule;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.dto.ScheduleData;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Schedule의 CRUD에 대한 HTTP 요청을 처리합니다.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 전체 Schedule 목록을 리턴합니다.
     *
     * @return 전체 Schedule의 목록
     */
    @GetMapping
    public List<Schedule> listSchedule() {
        return scheduleService.getAllSchedules();
    }

    /**
     * 고유 식별자로 Schedule을 검색해 리턴합니다.
     *
     * @param id Schedule 고유 식별자
     * @return 검색된 Schedule
     */
    @GetMapping("{id}")
    public Schedule detailSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    /**
     * Schedule을 생성합니다.
     *
     * @param scheduleData 생성하려는 Schedule 정보
     * @return 생성된 Schedule
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    public Schedule createSchedule(@RequestBody @Valid ScheduleData scheduleData) {
        return scheduleService.createSchedule(scheduleData);
    }

    /**
     * 식별자에 해당하는 Schedule 정보를 수정합니다.
     *
     * @param id Schedule 고유 식별자
     * @param scheduleData 수정하려는 Schedule 정보
     * @return 수정된 Schedule
     */
    @PatchMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    public Schedule updateSchedule(
            @PathVariable Long id,
            @RequestBody @Valid ScheduleData scheduleData
    ) {
        return scheduleService.updateSchedule(id, scheduleData);
    }

    /**
     * 특정 Schedule을 삭제합니다.
     *
     * @param id Schedule 고유 식별자
     */
    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchdule(id);
    }

}
