package com.rough.todo.common;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponce {
    private Integer status;
    private Object data;
    private Object error;

    public ApiResponce() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = error;
    }
}
