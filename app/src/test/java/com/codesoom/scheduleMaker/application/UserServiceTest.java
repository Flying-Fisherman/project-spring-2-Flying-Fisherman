package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.UserModificationData;
import com.codesoom.scheduleMaker.dto.UserRegistrationData;
import com.codesoom.scheduleMaker.errors.UserDataDuplicationException;
import com.codesoom.scheduleMaker.errors.UserNotFoundException;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    private UserService userService;
    private final UserRepository userRepository =
            mock(UserRepository.class);

    private static final Long REGISTERED_ID = 1L;
    private static final Long NOT_REGISTERED_ID = 100L;
    private static final String EXISTED_EMAIL_ADDRESS = "existed@example.com";

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userService = new UserService(mapper, userRepository, passwordEncoder);

        User user = User.builder()
                .uid(REGISTERED_ID)
                .id("tester")
                .password("password")
                .name("tester")
                .phone("010-0000-1111")
                .email("tester@test.com")
                .build();

        given(userRepository.save(any(User.class))).will(invocation -> {
            User source = invocation.getArgument(0);
            return User.builder()
                    .uid(1L)
                    .id("tester")
                    .password("password")
                    .name("tester")
                    .phone("010-0000-1111")
                    .email("tester@test.com")
                    .build();
        });

        given(userRepository.existsByEmail(EXISTED_EMAIL_ADDRESS))
                .willReturn(true);
        given(userRepository.existsById(any(String.class)))
                .willReturn(false);
        given(userRepository.existsByPhone(any(String.class)))
                .willReturn(false);

        given(userRepository.findByUid(REGISTERED_ID))
                .willReturn(Optional.of(
                        User.builder()
                                .uid(REGISTERED_ID)
                                .id("tester")
                                .password("password")
                                .name("tester")
                                .phone("010-0000-1111")
                                .email("tester@test.com")
                                .build()
                ));

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

        assertThat(user.getUid()).isEqualTo(REGISTERED_ID);
        assertThat(user.getName()).isEqualTo("tester");
        assertThat(user.getEmail()).isEqualTo("tester@test.com");

        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUserWithDuplicatedData() {
        UserRegistrationData registrationData = UserRegistrationData.builder()
                .id("tester")
                .password("password")
                .name("tester")
                .phone("010-0000-1111")
                .email(EXISTED_EMAIL_ADDRESS)
                .build();

        assertThatThrownBy(() -> userService.registerUser(registrationData))
                .isInstanceOf(UserDataDuplicationException.class);

        verify(userRepository).existsByEmail(EXISTED_EMAIL_ADDRESS);
    }

    @Test
    void updateUserWithExistedId() {
        UserModificationData modificationData = UserModificationData.builder()
                .password("newPassword")
                .name("newTester")
                .phone("010-0000-1111")
                .email("newTester@test.com")
                .build();

        Long uid = REGISTERED_ID;
        User user = userService.updateUser(uid, uid, modificationData);

        assertThat(user.getUid()).isEqualTo(REGISTERED_ID);
        assertThat(user.getEmail()).isEqualTo("newTester@test.com");
        assertThat(user.getName()).isEqualTo("newTester");

        verify(userRepository).findByUid(REGISTERED_ID);
    }

    @Test
    void updateUserWithNotExistedId() {
        UserModificationData modificationData = UserModificationData.builder()
                .password("newPassword")
                .name("newTester")
                .phone("010-0000-1111")
                .email("newTester@test.com")
                .build();

        Long uid = NOT_REGISTERED_ID;

        assertThatThrownBy(() -> userService.updateUser(uid, uid, modificationData))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void updateUserByOtherUser() {
        UserModificationData modificationData = UserModificationData.builder()
                .password("newPassword")
                .name("newTester")
                .phone("010-0000-1111")
                .email("newTester@test.com")
                .build();

        assertThatThrownBy(() -> userService.updateUser(REGISTERED_ID, NOT_REGISTERED_ID, modificationData))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    void deleteUser() {
        User user = userService.deleteUser(REGISTERED_ID);

        verify(userRepository).delete(user);
    }

    @Test
    void deleteUserWithNotExisted() {
        assertThatThrownBy(() -> userService.deleteUser(NOT_REGISTERED_ID))
                .isInstanceOf(UserNotFoundException.class);
    }
}
