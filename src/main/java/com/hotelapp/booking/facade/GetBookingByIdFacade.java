package com.hotelapp.booking.facade;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.response.BookingReport;

public interface GetBookingByIdFacade {

    Booking getBookingById(Long id);
}
