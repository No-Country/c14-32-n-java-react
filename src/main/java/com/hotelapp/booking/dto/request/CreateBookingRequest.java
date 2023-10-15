package com.hotelapp.booking.dto.request;

import com.hotelapp.room.dto.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    private Long idBooking;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer guestNumber;
    private BigDecimal price;
    private Long idRoom;
}
