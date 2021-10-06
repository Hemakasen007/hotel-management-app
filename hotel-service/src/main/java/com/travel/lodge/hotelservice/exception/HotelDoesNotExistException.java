package com.travel.lodge.hotelservice.exception;

import com.travel.lodge.hotelservice.util.Consts;

public class HotelDoesNotExistException extends RuntimeException {

    public HotelDoesNotExistException() {
        super(Consts.ExceptionMessage.USER_DOESNT_EXIST);
    }
}
