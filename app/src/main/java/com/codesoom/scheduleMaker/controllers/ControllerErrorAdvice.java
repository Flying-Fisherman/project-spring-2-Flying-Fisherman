package com.codesoom.scheduleMaker.controllers;

import com.codesoom.scheduleMaker.dto.ErrorResponse;
import com.codesoom.scheduleMaker.errors.ScheduleNotFoundException;
import com.codesoom.scheduleMaker.errors.UserDataDuplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 에러 발생 시의 예외처리를 담당합니다.
 */
@ResponseBody
@ControllerAdvice
public class ControllerErrorAdvice {

    /**
     * 특정 Scheduel이 존재하지 않을 때 ScheduleNotFoundException을 던집니다.
     *
     * @return 특정 Schedule이 존재하지 않는다는 메세지
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ScheduleNotFoundException.class)
    public ErrorResponse handleScheduleNotFound() {
        return new ErrorResponse("Schedule not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDataDuplicationException.class)
    public ErrorResponse handleUserDataDuplication() {
        return new ErrorResponse("User's some data is already existed");
    }
}
