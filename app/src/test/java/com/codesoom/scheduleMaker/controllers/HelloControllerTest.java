package com.codesoom.scheduleMaker.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {
    @Test
    @DisplayName("HelloController 기본 동작 테스트")
    void sayHello() {
        HelloController controller = new HelloController();

        assertThat(controller.sayHello()).contains("Hello, world");
    }
}