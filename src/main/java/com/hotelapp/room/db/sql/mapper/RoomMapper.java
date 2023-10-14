package com.hotelapp.room.db.sql.mapper;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class RoomMapper {
    public Room roomDataToRoom(RoomData roomData){
        List<BookingData> listBooking = null;
        if(!isNull(roomData.getListBooking())){
            listBooking = roomData.getListBooking();
        }
        return new Room.RoomBuilder()
                .idRoom(roomData.getIdRoom())
                .roomState(roomData.getRoomState())
                .roomNumber(roomData.getRoomNumber())
                .listBooking(listDataToBooking(listBooking))
                .build();
    }
    public List<Booking> listDataToBooking(List<BookingData> listData){
        if(isNull(listData)){
            return null;
        }
      return listData.stream().map(bookingData -> new Booking.BookingBuilder()
                .idBooking(bookingData.getIdBooking())
                .price(bookingData.getPrice())
                .checkInDate(bookingData.getCheckInDate())
                .checkOutDate(bookingData.getCheckOutDate())
                .build()
        ).collect(Collectors.toList());
    }
    public List<BookingData> listToBookingData(List<Booking> list){
        if(isNull(list)){
            return null;
        }
        return list.stream().map(booking -> new BookingData.BookingDataBuilder()
                .idBooking(booking.getIdBooking())
                .price(booking.getPrice())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .build()
        ).collect(Collectors.toList());
    }
    public RoomData roomToRoomData(Room room){
        List<Booking> listBooking = null;
        if(!isNull(room.getListBooking())){
            listBooking = room.getListBooking();
        }
        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .listBooking(listToBookingData(listBooking))
                .build();
    }
}
