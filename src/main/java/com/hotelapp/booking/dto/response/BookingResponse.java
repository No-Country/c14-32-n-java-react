package com.hotelapp.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Integer roomNumber;
    private BigDecimal price;
    private Date checkIn;
    private Date checkout;

    public static final class BookingResponseBuilder {
        private Integer roomNumber;
        private BigDecimal price;
        private Date checkIn;
        private Date checkout;

        public BookingResponseBuilder() {
        }

        public static BookingResponseBuilder aBookingResponse() {
            return new BookingResponseBuilder();
        }

        public BookingResponseBuilder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public BookingResponseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookingResponseBuilder checkIn(Date checkIn) {
            this.checkIn = checkIn;
            return this;
        }

        public BookingResponseBuilder checkout(Date checkout) {
            this.checkout = checkout;
            return this;
        }

        public BookingResponse build() {
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setRoomNumber(roomNumber);
            bookingResponse.setPrice(price);
            bookingResponse.setCheckIn(checkIn);
            bookingResponse.setCheckout(checkout);
            return bookingResponse;
        }
    }
}
