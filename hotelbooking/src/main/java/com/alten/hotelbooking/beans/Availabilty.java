package com.alten.hotelbooking.beans;

public class Availabilty {

    private long roomId ;
    private String startDate ;
    private String endDate ;
    private boolean isAvailable ;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Availabilty{" +
                "roomId=" + roomId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
