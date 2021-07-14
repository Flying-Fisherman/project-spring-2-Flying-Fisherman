package com.codesoom.scheduleMaker.application;

import com.codesoom.scheduleMaker.domain.Role;
import com.codesoom.scheduleMaker.domain.RoleRepository;
import com.codesoom.scheduleMaker.domain.User;
import com.codesoom.scheduleMaker.domain.UserRepository;
import com.codesoom.scheduleMaker.dto.RoleData;
import com.codesoom.scheduleMaker.errors.InvalidTokenException;
import com.codesoom.scheduleMaker.errors.LoginFailException;
import com.codesoom.scheduleMaker.utils.JwtUtil;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AuthenticationServiceTest {
    private static final String SECRET =  "FlyingFishermanScheduleMakerCode";
    private static final Long REGISTERED_UID = 1L;
    private static final Long NOT_REGISTERED_UID = 100L;
    private static final String REGISTERED_ID = "tester";
    private static final String NOT_REGISTERED_ID = "otherTester";
    private static final String REGISTERED_PASSWORD = "password";
    private static final String NOT_REGISTERED_PASSWORD = "wrongPassword";
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVWlkIjoxfQ" +
            ".HHQ5R0pyORMj3cV6koxhX6ylQYCG4zaKr36Fk4OENzE";
    private static final String INVALID_TOKEN = VALID_TOKEN
            .replace('3', '4');


    private AuthenticationService authenticationService;
    private Mapper mapper;

    private UserRepository userRepository = mock(UserRepository.class);
    private RoleRepository roleRepository = mock(RoleRepository.class);

    private User user;

    @BeforeEach
    void setUp() {
        JwtUtil jwtUtil = new JwtUtil(SECRET);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        mapper = DozerBeanMapperBuilder.buildDefault();

        authenticationService = new AuthenticationService(
                        userRepository, passwordEncoder, jwtUtil, roleRepository, mapper);

        user = User.builder().uid(REGISTERED_UID).build();
        user.changePassword("password", passwordEncoder);

        RoleData roleData = RoleData.builder()
                .userUid(user.getUid())
                .roleName("USER")
                .build();

        given(userRepository.findById("tester"))
                .willReturn(Optional.of(user));

        given(roleRepository.save(any(Role.class)))
                .willReturn(mapper.map(roleData, Role.class));
    }

    @Test
    void loginWithRightData() {
        String accessToken = authenticationService.login(
                REGISTERED_ID, REGISTERED_PASSWORD);

        assertThat(accessToken).isEqualTo(VALID_TOKEN);

        verify(userRepository).findById("tester");
    }

    @Test
    void loginWithWrongId() {
        assertThatThrownBy(() ->
                authenticationService.login(NOT_REGISTERED_ID, REGISTERED_PASSWORD)
        )
                .isInstanceOf(LoginFailException.class);

        verify(userRepository).findById(NOT_REGISTERED_ID);
    }

    @Test
    void loginWithWrongPassword() {
        assertThatThrownBy(() ->
                authenticationService.login(REGISTERED_ID, NOT_REGISTERED_PASSWORD)
        )
                .isInstanceOf(LoginFailException.class);

        verify(userRepository).findById(REGISTERED_ID);
    }

    @Test
    void parseTokenWithValidToken() {
        Long userUid = authenticationService.parseToken(VALID_TOKEN);

        assertThat(userUid).isEqualTo(REGISTERED_UID);
    }

    @Test
    void parseTokenWithInvalidToken() {
        assertThatThrownBy(
                () -> authenticationService.parseToken(INVALID_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    void setBasicRole() {
        Role role = authenticationService.setBasicRole(user);

        assertThat(role.getRoleName()).isEqualTo("USER");
    }
}
