package com.mynews.frontweb.config.security;


import com.mynews.frontweb.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthUser implements UserDetails {
    private Users users;
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final Boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }


    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(Users users) {
        return new User(users.getEmail(),
        users.getPassword(),
        users.getRole().getAuthorities());
    }
}
