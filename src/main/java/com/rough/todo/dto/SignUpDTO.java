package com.rough.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpDTO {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
