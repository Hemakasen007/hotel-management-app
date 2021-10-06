package com.travel.lodge.authservice.controller.exception.handler;

import com.travel.lodge.authservice.dto.ErrorDetails;
import com.travel.lodge.authservice.exception.UserExistsException;
import com.travel.lodge.authservice.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistsException.class)
    public ErrorDetails handleValidationExceptions(UserExistsException ex) {
        return new ErrorDetails(HttpStatus.CONFLICT.name(), Consts.ResponseMessages.FAILED_ADD_USER,
                Consts.ExceptionMessage.USER_EXISTS, null) ;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDetails handleValidationExceptions(Exception ex) {
        return new ErrorDetails(HttpStatus.CONFLICT.name(), Consts.ResponseMessages.FAILED_ADD_USER,
                Consts.ExceptionMessage.USER_EXISTS, null) ;
    }


}
