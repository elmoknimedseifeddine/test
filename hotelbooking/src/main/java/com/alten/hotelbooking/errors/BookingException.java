package com.alten.hotelbooking.errors;

public class BookingException extends Exception{

    public static final String MISSING_REQUEST = "MISSING_REQUEST" ;
    public static final String REQUEST_NOT_VALID = "REQUEST_NOT_VALID" ;
    public static final String ROOM_NOT_FOUND = "ROOM_NOTFOUND" ;
    public static final String RESERVATION_NOT_FOUND = "RESERVATION_NOT_FOUND" ;
    public static final String RESERVATION_ID_NOT_VALID = "RESERVATION_ID_NOT_VALID" ;
    public static final String END_DATE_BEFORE_START_DATE = "END_DATE_BEFORE_START_DATE" ;
    public static final String COULD_NOT_DELETE_OLD_RESERVATION = "COULD_NOT_DELETE_OLD_RESERVATION" ;




    public BookingException(String message){
        super(message);
    }
}
