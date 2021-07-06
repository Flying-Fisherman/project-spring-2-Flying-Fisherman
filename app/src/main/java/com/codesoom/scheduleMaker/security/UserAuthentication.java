package com.codesoom.scheduleMaker.security;

import com.codesoom.scheduleMaker.domain.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 접속한 회원의 정보를 확인, 반환합니다.
 */
public class UserAuthentication extends AbstractAuthenticationToken {
    private final Long uid;

    public UserAuthentication(Long uid, List<Role> roles) {
        super(authorities(roles));
        this.uid = uid;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return uid;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public String toString() {
        return "Authentication(" + uid + ")";
    }

    /**
     * 회원의 권한 목록을 리턴합니다.
     *
     * @param roles 회원의 권한
     * @return 회원의 권한 목록
     */
    private static List<GrantedAuthority> authorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 현재 접속한 회원의 UID를 반환합니다.
     *
     * @return 현재 접속한 회원의 UID
     */
    public Long getUserUid() {
        return uid;
    }
}
