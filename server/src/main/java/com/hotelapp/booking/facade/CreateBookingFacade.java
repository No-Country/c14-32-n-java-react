package com.hotelapp.booking.facade;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.room.dto.model.Room;

public interface CreateBookingFacade {
    Booking saveBooking(Booking booking);
}
