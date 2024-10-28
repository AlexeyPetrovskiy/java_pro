package ru.innotech.education.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.innotech.education.model.ErrorResponse;
import ru.innotech.education.service.PaymentsServiceException;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException e){
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), e.getMessage());
    }

    @ExceptionHandler(PaymentsServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEntityNotFoundException(PaymentsServiceException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), e.getMessage());
    }

}
