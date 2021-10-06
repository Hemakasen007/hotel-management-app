package com.travel.lodge.hotelservice.exception;

import com.travel.lodge.hotelservice.util.Consts;

public class InvalidDateSelectionException extends RuntimeException{
    public InvalidDateSelectionException(String message) {
        super(message);
    }
}
