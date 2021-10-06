package com.travel.lodge.hotelservice.util;

public class Consts {
    private Consts(){}

    public static class ConstString{
        private ConstString(){}
        public static final String PREFIX = "REF";
    }

    public static class ExceptionMessage{
        private ExceptionMessage(){}
        public static final String USER_DOESNT_EXIST = "The User doesnt exist in the system";
        public static final String NO_ROOMS_AVAILABLE = "No vacant rooms available";
        public static final String TOKEN_EXPIRED = "token expired";
        public static final String DATE_VALIDATION_FAIL = "Invalid Date selection";
        public static final String DATE_CHECKOUT_EARLY = "Checkout should be later than Checkin";
        public static final String RESERVATION_NOT_FOUND = "Reservation not found for refNo";

    }

    public static class Log{
        private Log(){}
        public static final String BRACKETS_3 = "{} -> {} -> {}";
        public static final String BRACKETS_2 = "{} -> {}";

        public static final String ENTERED = "Entered";
        public static final String HOTEL_AVAILABLE = "Hotel {} available";

        //method names
        public static final String ADD_USER = "addUser";
        public static final String GET_HOTELS = "getHotels";
        public static final String HOTEL_AVAILABILITY = "checkHotelAvailability";
        public static final String USER_EXISTS = "User Exists";
    }

    public static class AppMessages{
        private AppMessages(){}
        public static final String AVAILABLE_ROOMS = "%s Rooms Available for entered date range";
        public static final String RESERVATION_MARKED = "Reservation Marked, Please make the payment with below link to confirm your reservation";
    }

    public enum ResponseMessages {
        FAILED_ADD_USER,
        SUCCESS,
        FAILED ;
    }

    public enum BookedStatus{
        BOOKED, VACANT;
    }

    public enum ReservationStatus{
        MARKED, RESERVED;
    }

    public enum InvoiceStatus{
        INVOICED, PAID;
    }
}
