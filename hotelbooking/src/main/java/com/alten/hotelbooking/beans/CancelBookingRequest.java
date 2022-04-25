package com.alten.hotelbooking.beans;

public class CancelBookingRequest {

    private Long reservationId ;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
