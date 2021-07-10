package com.codesoom.scheduleMaker.controllers;

import com.codesoom.scheduleMaker.dto.ErrorResponse;
import com.codesoom.scheduleMaker.errors.LoginFailException;
import com.codesoom.scheduleMaker.errors.ScheduleNotFoundException;
import com.codesoom.scheduleMaker.errors.UserDataDuplicationException;
import com.codesoom.scheduleMaker.errors.UserNotFoundException;
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

    /**
     * 등록하려는 회원정보 중 한 개 이상이 중복될 경우 UserDataDuplicationException을 던집니다.
     *
     * @return 회원정보 중 한 개 이상이 중복된다는 메세지
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDataDuplicationException.class)
    public ErrorResponse handleUserDataDuplication() {
        return new ErrorResponse("User's some data is already existed");
    }

    /**
     * 로그인이 실패하였을 경우 LoginFailException을 던집니다.
     *
     * @return 로그인이 실패하였다는 메세지
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginFailException.class)
    public ErrorResponse handleLoginFailException() {
        return new ErrorResponse("Log-in failed");
    }

    /**
     * 검색 결과에 맞는 회원정보가 존재하지 않을 때 UserNotFoundException을 던집니다.
     *
     * @return 검색 결과에 맞는 회원정보가 존재하지 않는다는 메세지
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFound() {
        return new ErrorResponse("User not found");
    }
}
