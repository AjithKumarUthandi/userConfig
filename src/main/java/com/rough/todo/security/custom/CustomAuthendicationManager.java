package com.rough.todo.security.custom;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rough.todo.entity.User;
import com.rough.todo.service.UserServiceImpl;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthendicationManager implements AuthenticationManager {
    private UserServiceImpl userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userServiceImpl.getUserByUserName(authentication.getName());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());

    }

}

// package com.rough.todo.security.custom;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.rough.todo.entity.User;
// import com.rough.todo.service.UserServiceImpl;

// @Component
// public class CustomAuthendicationManager implements AuthenticationManager {
// private UserServiceImpl userServiceImpl;
// private BCryptPasswordEncoder bCryptPasswordEncoder;

// @Override
// public Authentication authenticate(Authentication authentication) throws
// AuthenticationException {
// User user = userServiceImpl.getUserByUserName(authentication.getName());
// if
// (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(),
// user.getPassword())) {
// throw new BadCredentialsException("Incorrect password");
// }
// return new UsernamePasswordAuthenticationToken(authentication.getName(),
// user.getPassword());

// }

// public CustomAuthendicationManager(UserServiceImpl userServiceImpl,
// BCryptPasswordEncoder bCryptPasswordEncoder) {
// this.userServiceImpl = userServiceImpl;
// this.bCryptPasswordEncoder = bCryptPasswordEncoder;
// }

// }
