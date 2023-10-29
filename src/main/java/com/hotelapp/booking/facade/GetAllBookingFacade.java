package com.hotelapp.booking.facade;

import com.hotelapp.booking.dto.model.Booking;
import org.springframework.data.domain.Page;

public interface GetAllBookingFacade {
    Page<Booking> getAllBookings(int numberPage);
}
