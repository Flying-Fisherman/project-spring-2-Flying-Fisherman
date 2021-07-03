package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.UserRegistrationData;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserServiceTest {
    private UserService userService;
    private final UserRepository userRepository =
            mock(UserRepository.class);

    private static final Long REGISTERED_ID = 1L;
    private static final Long NOT_REGISTERED_ID = 100L;

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        userService = new UserService(mapper, userRepository);

        User user = User.builder()
                .uid(1L)
                .id("tester")
                .password("password")
                .name("tester")
                .phone("010-0000-1111")
                .email("tester@test.com")
                .build();

    }

    @Test
    void registerUser() {
        UserRegistrationData registrationData = UserRegistrationData.builder()
                .id("tester")
                .password("password")
                .name("tester")
                .phone("010-0000-1111")
                .email("tester@test.com")
                .build();

        User user = userService.registerUser(registrationData);
    }
}