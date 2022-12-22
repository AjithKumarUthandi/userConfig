package com.rough.todo.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rough.todo.dto.SignUpDTO;
import com.rough.todo.entity.User;
import com.rough.todo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user);
    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user);
    }

    @Override
    public User saveUser(SignUpDTO userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Long getIdByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return user.get().getId();
    }

    static User unwrapUser(Optional<User> entity) {
        if (entity.isPresent())
            return entity.get();

        return null;
    }
}
