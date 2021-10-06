package com.travel.lodge.hotelservice.exception;

import static com.travel.lodge.hotelservice.util.Consts.ExceptionMessage.RESERVATION_NOT_FOUND;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException() {
        super(RESERVATION_NOT_FOUND);
    }
}
