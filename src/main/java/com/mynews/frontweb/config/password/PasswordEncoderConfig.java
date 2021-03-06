package com.mynews.frontweb.config.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y,12);
    }
}
