package com.hotelapp.booking.dto.response;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.model.enums.PaymentType;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.room.dto.response.RoomDataResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


public record BookingReport(Long idBooking, Date checkInDate, Date checkOutDate,
                            Date date, BookingState bookingState, BigDecimal price, PaymentType paymentType,
                            RoomDataResponse room, Customer customer) {
    public BookingReport(Booking booking) {
        this(booking.getIdBooking(),booking.getCheckInDate(),booking.getCheckOutDate(),
                booking.getDate(),booking.getBookingState(),
                booking.getPrice(),booking.getPaymentType() ,new RoomDataResponse(booking.getRoom()),
                booking.getCustomer());
    }
}
