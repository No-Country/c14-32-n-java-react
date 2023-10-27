package com.hotelapp.room.dto.mappers;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.response.BookingResponse;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.ReserveRoomRequest;
import org.springframework.stereotype.Component;

@Component
public class ReserveRoomMapper {

    public BookingResponse RoomToReserveRoomRequest(Room room, Booking booking){
        return new BookingResponse.BookingResponseBuilder()
                .roomNumber(room.getRoomNumber())
                .checkIn(booking.getCheckInDate())
                .checkout(booking.getCheckOutDate())
                .build();
    }

}
