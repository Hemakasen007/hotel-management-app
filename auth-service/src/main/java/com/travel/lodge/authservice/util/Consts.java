package com.travel.lodge.authservice.util;

public class Consts {

    public static class Logs{
        public static final String BRACKETS_2 = "{} -> {}";
        public static final String BRACKETS_3 = "{} -> {} -> {}";
        public static final String ENTERED = "Entered";

        public static final String ADD_USER = "addUser";



    }

    public static class ResponseMessages {
        public static final String FAILED_ADD_USER = "ADD_USER_FAILED";
        public static final String CREATED_USER = "USER_CREATED";
    }

    public static class ExceptionMessage{
        public static final String USER_EXISTS = "USER_ALREADY_EXISTS";
    }
}
