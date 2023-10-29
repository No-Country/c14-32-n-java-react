package com.hotelapp.booking.services.helper;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.categoryRoom.facade.GetCategoryByIdFacade;
import com.hotelapp.customer.facade.GetCustomerByIdFacade;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import com.hotelapp.room.services.ReserveRoomService;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingHelper {
    private final GetRoomByIdFacade getRoomByIdFacade;
    private final GetCustomerByIdFacade getCustomerByIdFacade;
    private final GetCategoryByIdFacade getCategoryByIdFacade;
    private final ReserveRoomService reserveRoomService;


    public CreateBookingHelper(GetRoomByIdFacade getRoomByIdFacade,GetCustomerByIdFacade getCustomerByIdFacade,
                               ReserveRoomService reserveRoomService,GetCategoryByIdFacade getCategoryByIdFacade) {
        this.getRoomByIdFacade = getRoomByIdFacade;
        this.getCustomerByIdFacade = getCustomerByIdFacade;
        this.reserveRoomService = reserveRoomService;
        this.getCategoryByIdFacade = getCategoryByIdFacade;
    }

    public void setBookingRoom(Booking booking){
        booking.setRoom(getRoomByIdFacade.getRoomById(booking.getRoom().getIdRoom()));
    }
    public void setBookingCustomer(Booking booking){
        booking.setCustomer(getCustomerByIdFacade.getCustomerById(booking.getCustomer().getIdCustomer()));

    }

}
