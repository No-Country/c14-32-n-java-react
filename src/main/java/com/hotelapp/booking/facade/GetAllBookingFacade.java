package com.hotelapp.booking.facade;

import com.hotelapp.booking.dto.response.BookingReport;
import org.springframework.data.domain.Page;

public interface GetAllBookingFacade {
    Page<BookingReport> getAllBookings(int numberPage);
}
