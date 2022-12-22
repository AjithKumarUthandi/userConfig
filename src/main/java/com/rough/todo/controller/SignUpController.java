package com.rough.todo.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rough.todo.dto.SignUpDTO;
import com.rough.todo.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class SignUpController {
    UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody SignUpDTO userdto) {
        userService.saveUser(userdto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
