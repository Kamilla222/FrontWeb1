package com.mynews.frontweb.service.impl;

import com.mynews.frontweb.config.Role;
import com.mynews.frontweb.model.Users;
import com.mynews.frontweb.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                user.get().getEmail(), user.get().getPassword().toLowerCase(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, user.get().getRole().getAuthorities());
    }

   //Stream.of(Role.values()).map(Role::name).collect(Collectors.toList(user.get().getRole())
    //EnumSet.allOf(Role.class).stream().map(Role::name).collect(Collectors.toList(user.get().getRole())

//    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
}