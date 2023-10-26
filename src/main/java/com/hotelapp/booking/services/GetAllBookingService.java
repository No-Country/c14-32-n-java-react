package com.hotelapp.booking.services;

import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.facade.GetAllBookingFacade;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetAllBookingService {
    private final GetAllBookingFacade getAllBookingFacade;


    public GetAllBookingService(GetAllBookingFacade getAllBookingFacade) {
        this.getAllBookingFacade = getAllBookingFacade;
    }
    public Page<BookingReport> getAllBookingsPage(int numberPage){
        return getAllBookingFacade.getAllBookings(numberPage);
    }
}
