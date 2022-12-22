package com.rough.todo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.RequestScope;

import com.rough.todo.dto.RequestMeta;

@Configuration
public class CommanBean {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @RequestScope
    public RequestMeta requestMeta() {
        return new RequestMeta();
    }
}
