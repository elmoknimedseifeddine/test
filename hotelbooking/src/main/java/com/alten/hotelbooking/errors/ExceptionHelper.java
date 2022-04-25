package com.alten.hotelbooking.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHelper {

    private static final Logger logger =  LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {BookingException.class})
    public ResponseEntity<Object> handleInvalidInput(BookingException exception){
        logger.error("Business Exception: ",exception.getMessage());
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
    }


}
