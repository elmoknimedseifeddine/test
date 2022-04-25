package com.alten.hotelbooking;

import com.alten.hotelbooking.errors.BookingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date formatDate(String date) throws BookingException {
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            return dateFormat.parse(date) ;
        }catch (ParseException e) {
            throw new BookingException("DATE FORMAT INVALID "+date) ;
        }
    }
}
