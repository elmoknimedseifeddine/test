package com.alten.hotelbooking.controllers;

import com.alten.hotelbooking.beans.*;
import com.alten.hotelbooking.errors.BookingException;
import com.alten.hotelbooking.services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);


    @Autowired
    private BookingService bookingService;


    @GetMapping(value = "/check-availability", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Availabilty> isAvailable(@PathParam("roomId") Long roomId, @PathParam("start") String start, @PathParam("end") String end) throws BookingException {
        if (null == roomId || roomId.equals(0L)) {
            throw new BookingException("ROOMID MISSING OR INVALID");
        }

        if (!StringUtils.hasLength(start)) {
            throw new BookingException("STARTDATE MISSING OR INVALID");
        }

        if (!StringUtils.hasLength(end)) {
            throw new BookingException("ENDDATE MISSING OR INVALID");
        }

        return new ResponseEntity<>(this.bookingService.isAvailable(roomId, start, end), HttpStatus.OK);

    }

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingResponse> bookRoom(@RequestBody BookingRequest request) throws BookingException {
        logger.info("booking room started");
        if (null == request) {
            logger.error("error booking room missing request");
            throw new BookingException(BookingException.MISSING_REQUEST);
        }
        return new ResponseEntity<>(this.bookingService.bookRoom(request), HttpStatus.OK);
    }

    @PostMapping(value = "/cancelBooking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CancelBookingResponse> cancelBooking(@RequestBody CancelBookingRequest request) throws BookingException {
        logger.info("cancel booking room started");
        if (null == request) {
            logger.error("error cancel booking room  missing request");
            throw new BookingException(BookingException.MISSING_REQUEST);
        }
        return new ResponseEntity<>(this.bookingService.cancelBooking(request), HttpStatus.OK);
    }

    @PostMapping(value = "/update-booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingResponse> updateBooking(@RequestBody BookingRequest request) throws BookingException {
        logger.info("update booking room started");
        if (null == request) {
            logger.error("error update booking room missing request");
            throw new BookingException(BookingException.MISSING_REQUEST);
        }
        return new ResponseEntity<>(this.bookingService.updateBooking(request), HttpStatus.OK);
    }


}
