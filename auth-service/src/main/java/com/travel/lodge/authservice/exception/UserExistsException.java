package com.travel.lodge.authservice.exception;

import com.travel.lodge.authservice.util.Consts;

public class UserExistsException extends RuntimeException{

    public UserExistsException(){
        super(Consts.ExceptionMessage.USER_EXISTS);
    }
}
