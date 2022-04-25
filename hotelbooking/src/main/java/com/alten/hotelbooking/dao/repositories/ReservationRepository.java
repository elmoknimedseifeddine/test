package com.alten.hotelbooking.dao.repositories;

import com.alten.hotelbooking.dao.entities.ReservationEntity;
import com.alten.hotelbooking.dao.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity , Long> {

    public Optional<List<ReservationEntity>> findByRoomAndStartGreaterThanAndEndLessThan(RoomEntity room , Date start , Date end) ;
}
