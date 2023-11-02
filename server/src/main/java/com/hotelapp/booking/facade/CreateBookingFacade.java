package com.hotelapp.booking.facade;

import com.hotelapp.booking.dto.model.Booking;

public interface CreateBookingFacade {
    Booking saveBooking(Booking booking);
}
