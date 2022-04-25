package com.alten.hotelbooking.services;

import com.alten.hotelbooking.beans.*;
import com.alten.hotelbooking.dao.entities.ClientEntity;
import com.alten.hotelbooking.dao.entities.ReservationEntity;
import com.alten.hotelbooking.dao.entities.RoomEntity;
import com.alten.hotelbooking.dao.repositories.ClientRepository;
import com.alten.hotelbooking.dao.repositories.ReservationRepository;
import com.alten.hotelbooking.dao.repositories.RoomRepository;
import com.alten.hotelbooking.errors.BookingException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceTest.class);

    private BookingService bookingService ;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.bookingService = new BookingService(roomRepository , reservationRepository , clientRepository) ;
    }

    @Test
    void isAvailable_ROOM_NOT_FOUND() {
        Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty()) ;
        try {
            this.bookingService.isAvailable(1L, "2022-05-01" ,"2022-05-01" ) ;
        } catch (BookingException e) {
            logger.error("isAvailable_ROOM_NOT_FOUND {}" , e.getMessage());
            Assertions.assertEquals(BookingException.ROOM_NOT_FOUND , e.getMessage());
        }
    }

    @Test
    void isAvailable_END_DATE_BEFORE_START_DATE() {
        RoomEntity room = new RoomEntity() ;
        room.setCapacity(4);
        room.setNumber(1);
        room.setId(1L);
        Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;
        try {
            this.bookingService.isAvailable(1L , "2022-06-01" ,"2022-05-01" ) ;
        } catch (BookingException e) {
            logger.error("isAvailable_REQUEST_NOT_VALID: {}" , e.getMessage());
            Assertions.assertEquals(BookingException.END_DATE_BEFORE_START_DATE , e.getMessage());
        }
    }

    @Test
    void isAvailable_OK() {
        RoomEntity room = new RoomEntity() ;
        room.setCapacity(4);
        room.setNumber(1);
        room.setId(1L);
        Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;

        Mockito.lenient().when(reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(Mockito.any(RoomEntity.class) , Mockito.any() , Mockito.any() ))
                .thenReturn(Optional.of(new ArrayList<ReservationEntity>()));

        try {
            Availabilty availabilty =  this.bookingService.isAvailable(1L , "2022-05-01" ,"2022-05-10" ) ;
            logger.info("isAvailable_OK availibilty: {}" , availabilty.toString());
            Assertions.assertTrue(availabilty.isAvailable());
        } catch (BookingException e) {
            logger.error("isAvailable_OK: {}" , e.getMessage());
        }
    }

    @Test
    void isAvailable_KO() {
        RoomEntity room = new RoomEntity() ;
        room.setCapacity(4);
        room.setNumber(1);
        room.setId(1L);
        Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;

        ReservationEntity reservation = new ReservationEntity() ;
        reservation.setRoom(room);
        List<ReservationEntity> reservations = new ArrayList<>();
        reservations.add(reservation) ;

        Mockito.lenient().when(reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(Mockito.any(RoomEntity.class) , Mockito.any() , Mockito.any() ))
                .thenReturn(Optional.of(reservations));

        try {
            Availabilty availabilty =  this.bookingService.isAvailable(1L , "2022-05-01" ,"2022-05-10" ) ;
            logger.info("isAvailable_KO availibilty: {}" , availabilty.toString());
            Assertions.assertFalse(availabilty.isAvailable());
        } catch (BookingException e) {
            logger.error("isAvailable_KO: {}" , e.getMessage());
        }
    }

    @Test
    void bookRoom_MISSING_REQUEST(){
        logger.info("bookRoom_MISSING_REQUEST");
        try {
            bookingService.bookRoom(null) ;
        } catch (BookingException e) {
            Assertions.assertEquals(BookingException.MISSING_REQUEST , e.getMessage());
        }
    }

    @Test
    void bookRoom_REQUEST_NOT_VALID(){
        logger.info("bookRoom_REQUEST_NOT_VALID");
        try {
            BookingRequest request = new BookingRequest() ;
            bookingService.bookRoom(request) ;
        } catch (BookingException e) {
            Assertions.assertEquals(BookingException.REQUEST_NOT_VALID , e.getMessage());
        }
    }

    @Test
    void bookRoom_ROOM_NOT_FOUND(){
        logger.info("bookRoom_ROOM_NOT_FOUND");
        try {
            BookingRequest request = new BookingRequest() ;
            request.setRoomId(2L);
            request.setStart("2022-05-01");
            request.setEnd("2022-05-10");
            Client client = new Client() ;
            client.setEmail("test@test.com");
            client.setName("test");
            List<Client> clients =new ArrayList<>() ;
            clients.add(client) ;
            request.setClients(clients);
            bookingService.bookRoom(request) ;
        } catch (BookingException e) {
            Assertions.assertEquals(BookingException.ROOM_NOT_FOUND , e.getMessage());
        }
    }

    @Test
    void bookRoom_ROOM_NOT_VACANT(){
        logger.info("bookRoom_ROOM_NOT_FOUND");
        try {
            BookingRequest request = new BookingRequest() ;
            request.setRoomId(2L);
            request.setStart("2022-05-01");
            request.setEnd("2022-05-10");
            Client client = new Client() ;
            List<Client> clients =new ArrayList<>() ;
            clients.add(client) ;
            request.setClients(clients);


            RoomEntity room = new RoomEntity() ;
            room.setCapacity(4);
            room.setNumber(1);
            room.setId(1L);
            Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;


            ReservationEntity reservation = new ReservationEntity() ;
            reservation.setRoom(room);
            List<ReservationEntity> reservations = new ArrayList<>();
            reservations.add(reservation) ;

            Mockito.lenient().when(reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(Mockito.any(RoomEntity.class) , Mockito.any() , Mockito.any() ))
                    .thenReturn(Optional.of(reservations));


            BookingResponse response = bookingService.bookRoom(request) ;
            Assertions.assertEquals(  BookingService.ROOM_NOT_VACANT , response.getMessage());
            Assertions.assertFalse(response.isSuccess() );

        } catch (BookingException e) {
        }
    }
    @Test
    void bookRoom_TOO_MANY_CLIENTS(){
        logger.info("bookRoom_TOO_MANY_CLIENTS");
        try {
            BookingRequest request = new BookingRequest() ;
            request.setRoomId(2L);
            request.setStart("2022-05-01");
            request.setEnd("2022-05-10");
            Client client = new Client() ;
            List<Client> clients =new ArrayList<>() ;
            clients.add(client) ;
            request.setClients(clients);


            RoomEntity room = new RoomEntity() ;
            room.setCapacity(0);
            room.setNumber(1);
            room.setId(1L);
            Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;


            Mockito.lenient().when(reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(Mockito.any(RoomEntity.class) , Mockito.any() , Mockito.any() ))
                    .thenReturn(Optional.of(new ArrayList<>()));


            BookingResponse response = bookingService.bookRoom(request) ;
            Assertions.assertEquals(  BookingService.TOO_MANY_CLIENTS , response.getMessage());
            Assertions.assertFalse(response.isSuccess() );

        } catch (BookingException e) {
        }
    }


    @Test
    void bookRoom_BAD_RESERVATION_END_DATE(){
        logger.info("bookRoom_BAD_RESERVATION_END_DATE");
        try {
            BookingRequest request = new BookingRequest() ;
            request.setRoomId(2L);
            request.setStart("2022-05-01");
            request.setEnd("2022-05-10");
            Client client = new Client() ;
            List<Client> clients =new ArrayList<>() ;
            clients.add(client) ;
            request.setClients(clients);


            RoomEntity room = new RoomEntity() ;
            room.setCapacity(2);
            room.setNumber(1);
            room.setId(1L);
            Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;


            Mockito.lenient().when(reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(Mockito.any(RoomEntity.class) , Mockito.any() , Mockito.any() ))
                    .thenReturn(Optional.of(new ArrayList<>()));


            BookingResponse response = bookingService.bookRoom(request) ;
            Assertions.assertEquals(  BookingService.BAD_RESERVATION_END_DATE , response.getMessage());
            Assertions.assertFalse(response.isSuccess() );

        } catch (BookingException e) {
        }
    }


    @Test
    void bookRoom_OK(){
        logger.info("bookRoom_BAD_RESERVATION_END_DATE");
        try {
            BookingRequest request = new BookingRequest() ;
            request.setRoomId(2L);
            request.setStart("2022-05-01");
            request.setEnd("2022-05-03");
            Client client = new Client() ;
            List<Client> clients =new ArrayList<>() ;
            clients.add(client) ;
            request.setClients(clients);


            RoomEntity room = new RoomEntity() ;
            room.setCapacity(2);
            room.setNumber(1);
            room.setId(1L);
            Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;


            Mockito.lenient().when(reservationRepository.findByRoomAndStartGreaterThanAndEndLessThan(Mockito.any(RoomEntity.class) , Mockito.any() , Mockito.any() ))
                    .thenReturn(Optional.of(new ArrayList<>()));

            ClientEntity c = new ClientEntity() ;
            List<ClientEntity> cs =  new ArrayList<>() ;
            cs.add(c);

            Mockito.lenient().when(clientRepository.saveAllAndFlush(Mockito.any())).thenReturn(cs);


            ReservationEntity reservation = new ReservationEntity() ;
            reservation.setId(1L);

            Mockito.lenient().when(reservationRepository.saveAndFlush(Mockito.any())).thenReturn(reservation);



            BookingResponse response = bookingService.bookRoom(request) ;
            Assertions.assertEquals( BookingService.OK , response.getMessage());
            Assertions.assertTrue(response.isSuccess() );

        } catch (BookingException e) {
        }
    }

    @Test
    void cancelBooking_RESERVATION_ID_NOT_VALID(){

        CancelBookingRequest request = new CancelBookingRequest() ;
        try {
            bookingService.cancelBooking(request) ;
        } catch (BookingException e) {
            Assertions.assertEquals(BookingException.RESERVATION_ID_NOT_VALID , e.getMessage());
        }

    }

    @Test
    void cancelBooking_RESERVATION_NOT_FOUND(){
        CancelBookingRequest request = new CancelBookingRequest() ;
        request.setReservationId(1L);

        Mockito.lenient().when(reservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty()) ;
        try {
            bookingService.cancelBooking(request) ;
        } catch (BookingException e) {
            Assertions.assertEquals(BookingException.RESERVATION_NOT_FOUND , e.getMessage());
        }
    }

    @Test
    void cancelBooking_OK(){
        CancelBookingRequest request = new CancelBookingRequest() ;
        request.setReservationId(1L);

        ClientEntity c = new ClientEntity() ;
        List<ClientEntity> cs =  new ArrayList<>() ;
        cs.add(c);

        Mockito.lenient().when(clientRepository.saveAllAndFlush(Mockito.any())).thenReturn(cs);

        RoomEntity room = new RoomEntity() ;
        room.setCapacity(4);
        room.setNumber(1);
        room.setId(1L);
        Mockito.lenient().when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(room)) ;

        ReservationEntity reservation = new ReservationEntity() ;
        reservation.setRoom(room);
        List<ReservationEntity> reservations = new ArrayList<>();
        reservations.add(reservation) ;

        Mockito.lenient().when(reservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reservation)) ;
        Mockito.lenient().when(clientRepository.findAllByReservation(Mockito.any())).thenReturn(Optional.of(cs)) ;



        try {
            CancelBookingResponse response =    bookingService.cancelBooking(request) ;
            Assertions.assertTrue(response.getCanceled());
        } catch (BookingException e) {
        }
    }
}