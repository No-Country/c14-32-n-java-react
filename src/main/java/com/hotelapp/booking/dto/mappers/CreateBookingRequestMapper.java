package com.hotelapp.booking.dto.mappers;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CreateBookingRequestMapper {

    public Booking createBookingRequestToBooking(CreateBookingRequest createBookingRequest){
        Long id = null;
        if(!isNull(createBookingRequest.getIdBooking())){
            id = createBookingRequest.getIdBooking();
        }
        Room room = new Room.RoomBuilder().idRoom(createBookingRequest.getIdRoom()).build();
        return new Booking.BookingBuilder()
                .price(createBookingRequest.getPrice())
                .guestNumber(createBookingRequest.getGuestNumber())
                .checkOutDate(createBookingRequest.getCheckOutDate())
                .checkInDate(createBookingRequest.getCheckInDate())
                .idBooking(id)
                .room(room)
                .build();
    }

}
