package com.alten.hotelbooking.beans;

public class BookingResponse {

    private BookingRequest request ;
    private long reservationId ;
    private boolean success ;
    private String message ;

    public BookingRequest getRequest() {
        return request;
    }

    public void setRequest(BookingRequest request) {
        this.request = request;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "request=" + request +
                ", reservationId=" + reservationId +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
