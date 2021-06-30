package com.codesoom.scheduleMaker.errors;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
        super("Schedule not found : " + id);
    }
}
