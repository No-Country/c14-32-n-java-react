package com.hotelapp.booking.dto.response;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record BookingReport(Long idBooking, Integer roomNumber, Long idCustomer, BigDecimal price, Date checkIn, Date checkout, LocalDateTime date, BookingState bookingState) {

    public BookingReport(Booking booking){
        this(booking.getIdBooking(), booking.getRoom().getRoomNumber(),
                booking.getCustomer().getIdCustomer(), booking.getPrice(),
                booking.getCheckInDate(),booking.getCheckOutDate(),
                booking.getDate(), booking.getBookingState());
    }
}
