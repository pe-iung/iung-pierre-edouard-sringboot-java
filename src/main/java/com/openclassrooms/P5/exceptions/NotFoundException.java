package com.openclassrooms.P5.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
        log.error("Not found exception = {}",message);
    }
}
