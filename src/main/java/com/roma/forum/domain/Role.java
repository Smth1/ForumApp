package com.roma.forum.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    Role() {
    }

    public String getAuthority() {
        return this.name();
    }
}
