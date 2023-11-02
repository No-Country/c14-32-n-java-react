package com.hotelapp.room.dto.mappers;

import com.hotelapp.booking.dto.response.BookingResponse;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

@Component
public class ReserveRoomMapper {

    public BookingResponse RoomToReserveRoomRequest(Room room, com.hotelapp.booking.dto.model.Booking booking){
        return new BookingResponse.BookingResponseBuilder()
                .roomNumber(room.getRoomNumber())
                .checkIn(booking.getCheckInDate())
                .checkout(booking.getCheckOutDate())
                .build();
    }

}
