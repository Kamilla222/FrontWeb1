package com.mynews.frontweb.config.security;

public enum Permission {
    USER_RAED("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
