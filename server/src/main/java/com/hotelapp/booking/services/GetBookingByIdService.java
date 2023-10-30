package com.hotelapp.booking.services;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.response.BookingReport;
import com.hotelapp.booking.facade.GetBookingByIdFacade;
import org.springframework.stereotype.Service;

@Service
public class GetBookingByIdService {

    private final GetBookingByIdFacade getBookingByIdFacade;

    public GetBookingByIdService(GetBookingByIdFacade getBookingByIdFacade){
        this.getBookingByIdFacade = getBookingByIdFacade;
    }

    public BookingReport getBookingById(Long id){
        Booking booking = getBookingByIdFacade.getBookingById(id);
        if (booking != null) return new BookingReport(booking);
        return null;
    }

}
