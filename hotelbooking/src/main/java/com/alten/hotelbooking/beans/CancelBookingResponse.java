package com.alten.hotelbooking.beans;

public class CancelBookingResponse {

    private CancelBookingRequest request ;
    private Boolean canceled ;

    public CancelBookingRequest getRequest() {
        return request;
    }

    public void setRequest(CancelBookingRequest request) {
        this.request = request;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }
}
