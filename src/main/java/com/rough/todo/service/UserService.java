package com.rough.todo.service;

import com.rough.todo.dto.SignUpDTO;
import com.rough.todo.entity.User;

public interface UserService {
    User getUserById(Long id);

    User getUserByUserName(String username);

    User saveUser(SignUpDTO user);

    Long getIdByUsername(String username);
}
