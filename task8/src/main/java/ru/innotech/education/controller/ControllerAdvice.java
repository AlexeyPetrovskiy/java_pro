package ru.innotech.education.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.innotech.education.dto.ErrorResponse;
import ru.innotech.education.service.LimitServiceException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(LimitServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEntityNotFoundException(LimitServiceException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), e.getMessage());
    }
}
