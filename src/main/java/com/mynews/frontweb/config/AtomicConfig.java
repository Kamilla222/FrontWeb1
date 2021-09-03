package com.mynews.frontweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AtomicConfig {

    @Bean
    public AtomicInteger atomicInteger(){
        return new AtomicInteger(0);
    }
}
