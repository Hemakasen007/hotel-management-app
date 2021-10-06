package com.travel.lodge.hotelservice.controller.error.handler;

import com.travel.lodge.hotelservice.dto.ErrorDetails;
import com.travel.lodge.hotelservice.exception.HotelDoesNotExistException;
import com.travel.lodge.hotelservice.exception.InvalidDateSelectionException;
import com.travel.lodge.hotelservice.exception.RoomsUnavailableException;
import com.travel.lodge.hotelservice.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDetails handleValidationExceptions(Exception ex) {
        return new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null, null);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HotelDoesNotExistException.class)
    public ErrorDetails handleUserCreationException(HotelDoesNotExistException ex) {
        return new ErrorDetails(HttpStatus.NOT_FOUND.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null, null);
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(RoomsUnavailableException.class)
    public ErrorDetails handleRoomsUnavailableException(RoomsUnavailableException ex) {
        return new ErrorDetails(HttpStatus.EXPECTATION_FAILED.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDateSelectionException.class)
    public ErrorDetails handleInvalidDateSelectionException(InvalidDateSelectionException ex) {
        return new ErrorDetails(HttpStatus.BAD_REQUEST.name(), Consts.ResponseMessages.FAILED.name(),
                ex.getMessage() , null, null);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        var result = e.getBindingResult();

        Map<String, Object> errorList;
        errorList = result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        result.getGlobalErrors().forEach(fieldError -> errorList.put("Error", fieldError.getDefaultMessage()));
        return new ErrorDetails(HttpStatus.BAD_REQUEST.name(), Consts.ResponseMessages.FAILED.name(),
                errorList);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorDetails handleAuthenticationException(AuthenticationException ex, HttpServletResponse response){
        var genericResponseBean = new ErrorDetails(HttpStatus.UNAUTHORIZED.name(), Consts.ResponseMessages.FAILED.name(),
                Consts.ExceptionMessage.TOKEN_EXPIRED, null);
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return genericResponseBean;
    }

}
