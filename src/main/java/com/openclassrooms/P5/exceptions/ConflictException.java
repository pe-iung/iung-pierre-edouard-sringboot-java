package com.openclassrooms.P5.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }

}
