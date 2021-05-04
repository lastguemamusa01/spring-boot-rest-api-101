package com.example.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExcetion extends RuntimeException {

    public UserNotFoundExcetion(String message) {
        super(message);
    }

}
