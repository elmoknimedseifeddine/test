package com.alten.hotelbooking.dao.repositories;

import com.alten.hotelbooking.dao.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity , Long> {
}
