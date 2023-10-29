package com.hotelapp.booking.dto.request;

import com.hotelapp.booking.dto.model.enums.PaymentType;
import java.util.Date;

public record CreateBookingRequest(
        Long idRoom,
        Long idCustomer,
        Date checkInDate,
        Date checkOutDate,
        PaymentType paymentType
) {
}