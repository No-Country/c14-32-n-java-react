package com.hotelapp.booking.services;

import com.hotelapp.booking.facade.DeleteBookingFacade;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookingService {

    private final DeleteBookingFacade deleteBookingFacade;

    public DeleteBookingService(DeleteBookingFacade deleteBookingFacade){
        this.deleteBookingFacade = deleteBookingFacade;
    }

    public void deleteBooking(Long id){
        deleteBookingFacade.deleteBooking(id);
    }
}
