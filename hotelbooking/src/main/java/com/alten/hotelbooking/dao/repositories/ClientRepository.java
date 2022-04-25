package com.alten.hotelbooking.dao.repositories;

import com.alten.hotelbooking.dao.entities.ClientEntity;
import com.alten.hotelbooking.dao.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity , Long> {

    public Optional<List<ClientEntity>> findAllByReservation(ReservationEntity reservation) ;
}
