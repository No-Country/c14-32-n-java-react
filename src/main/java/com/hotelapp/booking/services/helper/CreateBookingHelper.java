package com.hotelapp.booking.services.helper;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingHelper {
    private final GetRoomByIdFacade getRoomByIdFacade;


    public CreateBookingHelper(GetRoomByIdFacade getRoomByIdFacade) {
        this.getRoomByIdFacade = getRoomByIdFacade;
    }

    public void setBookingRoom(Booking booking){

        booking.setRoom(getRoomByIdFacade.getRoomById(booking.getRoom().getIdRoom()));
    }
}
