package com.hotelapp.booking.dto.request;

import com.hotelapp.booking.dto.model.enums.BookingState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    private Long idBooking;
    private Date checkInDate;
    private Date checkOutDate;
    private LocalDateTime date;
    private BookingState bookingState;
    private Long idCustomer;
    private Long idRoom;
}
