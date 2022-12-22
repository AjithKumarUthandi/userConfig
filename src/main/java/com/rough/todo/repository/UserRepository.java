package com.rough.todo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rough.todo.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
