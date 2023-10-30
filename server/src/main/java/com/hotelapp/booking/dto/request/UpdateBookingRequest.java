package com.hotelapp.booking.dto.request;

import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.model.enums.PaymentType;

import java.util.Date;

public record UpdateBookingRequest(
        Long idBooking,
        Long idRoom,
        Long idCustomer,
        Date checkInDate,
        Date checkOutDate,
        PaymentType paymentType,
        BookingState bookingState
) {
}
