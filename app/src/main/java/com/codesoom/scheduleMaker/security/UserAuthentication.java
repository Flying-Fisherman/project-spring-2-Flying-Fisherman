package com.codesoom.scheduleMaker.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserAuthentication extends AbstractAuthenticationToken {
    private final Long uid;

    public UserAuthentication(Long uid, List<Object> roles) {
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

    private static List<GrantedAuthority> authorities(List<Object> roles) {
        return null;
    }

    public Long getUserId() {
        return uid;
    }
}
