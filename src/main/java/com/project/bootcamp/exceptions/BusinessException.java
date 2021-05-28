package com.project.bootcamp.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }

}
