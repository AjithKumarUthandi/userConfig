package com.rough.todo.security;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.rough.todo.dto.RequestMeta;
import com.rough.todo.security.custom.CustomAuthendicationManager;
import com.rough.todo.security.filter.AuthenticationFilter;
import com.rough.todo.security.filter.ExceptionHanderFilter;
import com.rough.todo.security.filter.JWTAutherizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthendicationManager authendicationManager;
    private RequestMeta requestMeta;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authendicationManager);
        authenticationFilter.setFilterProcessesUrl("/login");
        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/todo/sign-up").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHanderFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAutherizationFilter(requestMeta), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
