package com.hotelapp.booking.dto.model;

import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.model.enums.RoomState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long idBooking;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer guestNumber;
    private BigDecimal price;
    private Room room;


    public static final class BookingBuilder {
        private Long idBooking;
        private Date checkInDate;
        private Date checkOutDate;
        private Integer guestNumber;
        private BigDecimal price;
        private Room room;

        public BookingBuilder() {
        }

        public static BookingBuilder aBooking() {
            return new BookingBuilder();
        }

        public BookingBuilder idBooking(Long idBooking) {
            this.idBooking = idBooking;
            return this;
        }

        public BookingBuilder checkInDate(Date checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public BookingBuilder checkOutDate(Date checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public BookingBuilder guestNumber(Integer guestNumber) {
            this.guestNumber = guestNumber;
            return this;
        }

        public BookingBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookingBuilder room(Room room) {
            this.room = room;
            return this;
        }

        public Booking build() {
            Booking booking = new Booking();
            booking.setIdBooking(idBooking);
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);
            booking.setGuestNumber(guestNumber);
            booking.setPrice(price);
            booking.setRoom(room);
            return booking;
        }
    }
}
