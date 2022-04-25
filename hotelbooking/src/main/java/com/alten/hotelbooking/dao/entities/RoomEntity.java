package com.alten.hotelbooking.dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "capacity", length = 5, nullable = true)
    private int capacity;


    @Column(name = "number", length = 5, nullable = true)
    private int number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", number=" + number +
                '}';
    }
}
