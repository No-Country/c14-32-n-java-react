package com.hotelapp.room.dto.response;

import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.model.enums.PaymentType;

import java.math.BigDecimal;

public record DataCheckResponse(Long idBooking, BookingState bookingState, BigDecimal price, PaymentType paymentType,RoomDataResponse room) {
}
