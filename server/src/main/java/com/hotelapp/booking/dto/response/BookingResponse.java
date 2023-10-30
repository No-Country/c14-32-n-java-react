package com.hotelapp.booking.dto.response;

import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Integer roomNumber;
    private BigDecimal price;
    private PaymentType paymentType;
    private Date checkIn;
    private Date checkout;
    private LocalDateTime date;
    private BookingState bookingState;

    public static final class BookingResponseBuilder {
        private Integer roomNumber;
        private BigDecimal price;
        private PaymentType paymentType;
        private Date checkIn;
        private Date checkout;
        private LocalDateTime date;
        private BookingState bookingState;

        public BookingResponseBuilder() {
        }

        public static BookingResponseBuilder aBookingResponse() {
            return new BookingResponseBuilder();
        }

        public BookingResponseBuilder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }


        public BookingResponseBuilder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public BookingResponseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookingResponseBuilder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
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
        public BookingResponseBuilder bookingState(BookingState bookingState) {
            this.bookingState = bookingState;
            return this;
        }



        public BookingResponse build() {
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setRoomNumber(roomNumber);
            bookingResponse.setPrice(price);
            bookingResponse.setPaymentType(paymentType);
            bookingResponse.setCheckIn(checkIn);
            bookingResponse.setCheckout(checkout);
            bookingResponse.setDate(date);
            return bookingResponse;
        }
    }
}
