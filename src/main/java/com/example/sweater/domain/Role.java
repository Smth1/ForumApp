package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    private Role() {
    }

    public String getAuthority() {
        return this.name();
    }
}
