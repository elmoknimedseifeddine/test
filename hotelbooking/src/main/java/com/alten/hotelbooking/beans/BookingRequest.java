package com.alten.hotelbooking.beans;

import java.util.List;

public class BookingRequest {

    private Long roomId ;
    private String start ;
    private String end ;
    private List<Client> clients ;
    private Long reservationId ;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "roomId=" + roomId +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", clients=" + clients +
                ", reservationId=" + reservationId +
                '}';
    }
}
