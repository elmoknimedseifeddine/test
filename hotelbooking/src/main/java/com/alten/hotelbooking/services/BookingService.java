package com.alten.hotelbooking.services;


import com.alten.hotelbooking.Utils;
import com.alten.hotelbooking.beans.*;
import com.alten.hotelbooking.dao.entities.ClientEntity;
import com.alten.hotelbooking.dao.entities.ReservationEntity;
import com.alten.hotelbooking.dao.entities.RoomEntity;
import com.alten.hotelbooking.dao.repositories.ClientRepository;
import com.alten.hotelbooking.dao.repositories.ReservationRepository;
import com.alten.hotelbooking.dao.repositories.RoomRepository;
import com.alten.hotelbooking.errors.BookingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    public static final String OK = "OK";
    public static final String ROOM_NOT_VACANT = "ROOM_NOT_VACANT";
    public static final String TOO_MANY_CLIENTS = "TOO_MANY_CLIENTS";
    public static final String BAD_RESERVATION_END_DATE = "BAD_RESERVATION_END_DATE";
    public static final String RESERVATION_TOO_LONG = "RESERVATION_TOO_LONG";


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Constructor for test purpose
    public BookingService(RoomRepository roomRepository, ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    public Availabilty isAvailable(Long roomId, String start, String end) throws BookingException {

        RoomEntity room = roomRepository.findById(roomId).orElse(null);
        if (null == room) {
            throw new BookingException(BookingException.ROOM_NOT_FOUND);
        }
        Date startDate = Utils.formatDate(start);
        Date endDate = Utils.formatDate(end);
        if (endDate.before(startDate)) {
            throw new BookingException(BookingException.END_DATE_BEFORE_START_DATE);
        }

        List<ReservationEntity> reservations = reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(room, startDate, endDate).orElse(new ArrayList<>());
        Availabilty availabilty = new Availabilty();
        availabilty.setAvailable(reservations.isEmpty());
        availabilty.setEndDate(end);
        availabilty.setStartDate(start);
        availabilty.setRoomId(roomId);
        return availabilty;
    }

    @Transactional
    public BookingResponse bookRoom(BookingRequest request) throws BookingException {
        if (null == request) {
            throw new BookingException(BookingException.MISSING_REQUEST);
        }

        if (null == request.getRoomId() || request.getRoomId().equals(0L) || !StringUtils.hasLength(request.getStart())
                || !StringUtils.hasLength(request.getEnd()) || request.getClients().isEmpty() ) {
            throw new BookingException(BookingException.REQUEST_NOT_VALID);
        }

        if(request.getClients().stream().anyMatch(client -> !StringUtils.hasLength(client.getName()) || !StringUtils.hasLength(client.getEmail()))){
            logger.error("email or name empty bad request");
            throw new BookingException(BookingException.REQUEST_NOT_VALID);
        }

        RoomEntity room = roomRepository.findById(request.getRoomId()).orElse(null);
        if (null == room) {
            throw new BookingException(BookingException.ROOM_NOT_FOUND);
        }

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setRequest(request);
        bookingResponse.setReservationId(0L);
        bookingResponse.setSuccess(false);

        Date startDate = Utils.formatDate(request.getStart());
        Date endDate = Utils.formatDate(request.getEnd());

        if (endDate.before(startDate)) {
            logger.error("Date order invalid start: {} end: {} ", startDate, endDate);
            throw new BookingException(BookingException.END_DATE_BEFORE_START_DATE);
        }

        List<ReservationEntity> reservations = reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(room, startDate, endDate).orElse(new ArrayList<>());

        if (!reservations.isEmpty()) {
            bookingResponse.setMessage(ROOM_NOT_VACANT);
            return bookingResponse;
        }


        if (room.getCapacity() < request.getClients().size()) {
            logger.error("too many client for room , room capacity: {} , client list size:{}", room.getCapacity(), request.getClients().size());
            bookingResponse.setMessage(TOO_MANY_CLIENTS);
            return bookingResponse;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 3);

        Date tendDate = calendar.getTime();

        if (tendDate.before(endDate)) {
            logger.error("reservation cannot last more than 3 days start: {} end: {} should end at {}", startDate, endDate, tendDate);
            bookingResponse.setMessage(BAD_RESERVATION_END_DATE);
            return bookingResponse;
        }


        long diff = ChronoUnit.DAYS.between(LocalDateTime.now(), startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        if (diff > 30) {
            logger.error("reservation length invalid start: {} end: {} length: {} ", startDate, endDate, diff);
            bookingResponse.setMessage(RESERVATION_TOO_LONG);
            return bookingResponse;
        }

        ReservationEntity reservation = new ReservationEntity();
        reservation.setRoom(room);
        reservation.setStart(startDate);
        reservation.setEnd(endDate);

        reservation = reservationRepository.saveAndFlush(reservation);

        ReservationEntity finalReservation = reservation;
        List<ClientEntity> it =
                request.getClients().stream().map(client -> {
                    ClientEntity clientEntity = new ClientEntity();
                    clientEntity.setEmail(client.getEmail());
                    clientEntity.setName(client.getName());
                    clientEntity.setOrganizer(client.isOrganizer());
                    clientEntity.setReservation(finalReservation);
                    return clientEntity;
                }).collect(Collectors.toList());
        clientRepository.saveAllAndFlush(it);

        bookingResponse.setReservationId(finalReservation.getId());
        bookingResponse.setSuccess(true);
        bookingResponse.setMessage(OK);

        return bookingResponse;
    }

    @Transactional
    public CancelBookingResponse cancelBooking(CancelBookingRequest request) throws BookingException {
        if (null == request.getReservationId() || request.getReservationId().equals(0L)) {
            throw new BookingException(BookingException.RESERVATION_ID_NOT_VALID);
        }

        ReservationEntity reservation = reservationRepository.findById(request.getReservationId()).orElse(null);
        if (null == reservation) {
            throw new BookingException(BookingException.RESERVATION_NOT_FOUND);
        }

        List<ClientEntity> clients = clientRepository.findAllByReservation(reservation).orElse(new ArrayList<>());
        clientRepository.deleteAll(clients);
        clientRepository.flush();

        reservationRepository.delete(reservation);
        reservationRepository.flush();


        CancelBookingResponse cancelBookingResponse = new CancelBookingResponse();
        cancelBookingResponse.setCanceled(true);
        cancelBookingResponse.setRequest(request);

        return cancelBookingResponse;

    }

    @Transactional
    public BookingResponse updateBooking(BookingRequest request) throws BookingException {

        if (null == request.getRoomId() || request.getRoomId().equals(0L) || !StringUtils.hasLength(request.getStart())
                || !StringUtils.hasLength(request.getEnd()) || request.getClients().isEmpty() || null == request.getReservationId() || request.getReservationId().equals(0L)) {
            throw new BookingException(BookingException.REQUEST_NOT_VALID);
        }
        CancelBookingRequest cancelBookingRequest = new CancelBookingRequest();
        cancelBookingRequest.setReservationId(request.getReservationId());
        CancelBookingResponse cancelBookingResponse = this.cancelBooking(cancelBookingRequest);
        if (Boolean.FALSE.equals(cancelBookingResponse.getCanceled())) {
            throw new BookingException(BookingException.COULD_NOT_DELETE_OLD_RESERVATION);
        }
        return this.bookRoom(request);
    }
}
