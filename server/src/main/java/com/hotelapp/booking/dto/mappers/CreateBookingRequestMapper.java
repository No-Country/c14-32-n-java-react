package com.hotelapp.booking.dto.mappers;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.booking.dto.request.UpdateBookingRequest;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
public class CreateBookingRequestMapper {

    public Booking createBookingRequestToBooking(CreateBookingRequest createBookingRequest){
        Room room = new Room.RoomBuilder().idRoom(createBookingRequest.idRoom()).build();

        Customer customer = new Customer.CustomerBuilder().idCustomer(createBookingRequest.idCustomer()).build();
        return new Booking.BookingBuilder()
                .price(new BigDecimal(0.0))
                .paymentType(createBookingRequest.paymentType())
                .customer(customer)
                .checkInDate(createBookingRequest.checkInDate())
                .checkOutDate(createBookingRequest.checkOutDate())
                .bookingState(BookingState.CONFIRMED)
                .room(room)
                .build();


    }

    public Booking updateBookingRequestToBooking(UpdateBookingRequest updateBookingRequest){

        Room room = new Room.RoomBuilder().idRoom(updateBookingRequest.idRoom()).build();
        Customer customer = new Customer.CustomerBuilder().idCustomer(updateBookingRequest.idCustomer()).build();
        return new Booking.BookingBuilder()
                .idBooking(updateBookingRequest.idBooking())
                .customer(customer)
                .checkInDate(updateBookingRequest.checkInDate())
                .checkOutDate(updateBookingRequest.checkOutDate())
                .idBooking(updateBookingRequest.idBooking())
                .price(new BigDecimal(0.0))
                .paymentType(updateBookingRequest.paymentType())
                .bookingState(updateBookingRequest.bookingState())
                .room(room)
                .build();
    }

}
