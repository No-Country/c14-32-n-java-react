package com.hotelapp.booking.services.helper;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.customer.facade.GetCustomerByIdFacade;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import com.hotelapp.room.services.ReserveRoomService;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingHelper {
    private final GetRoomByIdFacade getRoomByIdFacade;
    private final GetCustomerByIdFacade getCustomerByIdFacade;
    private final ReserveRoomService reserveRoomService;


    public CreateBookingHelper(GetRoomByIdFacade getRoomByIdFacade,GetCustomerByIdFacade getCustomerByIdFacade,
                               ReserveRoomService reserveRoomService) {
        this.getRoomByIdFacade = getRoomByIdFacade;
        this.getCustomerByIdFacade = getCustomerByIdFacade;
        this.reserveRoomService = reserveRoomService;
    }

    public void setBookingRoom(Booking booking){
        Room room = getRoomByIdFacade.getRoomById(booking.getRoom().getIdRoom());
        //room.setRoomState(RoomState.RESERVED);
        booking.setRoom(room);
    }
    public void setBookingCustomer(Booking booking){
        booking.setCustomer(getCustomerByIdFacade.getCustomerById(booking.getCustomer().getIdCustomer()));
    }

}
