package com.hotelapp.booking.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.model.enums.PaymentType;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.response.RoomDataResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long idBooking;
    private Date checkInDate;
    private Date checkOutDate;
    private Date date;
    private BookingState bookingState;
    private Customer customer;
    private BigDecimal price;
    private PaymentType paymentType;
    private Room room;


    public static final class BookingBuilder {
        private Long idBooking;
        private Date checkInDate;
        private Date checkOutDate;
        private Date date;
        private BookingState bookingState;
        private Customer customer;
        private BigDecimal price;
        private PaymentType paymentType;
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

        public BookingBuilder date(Date date) {
            this.date = date;
            return this;
        }
        public BookingBuilder bookingState(BookingState bookingState) {
            this.bookingState = bookingState;
            return this;
        }

        public BookingBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public BookingBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookingBuilder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
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
            booking.setDate(date);
            booking.setBookingState(bookingState);
            booking.setCustomer(customer);
            booking.setPrice(price);
            booking.setPaymentType(paymentType);
            booking.setRoom(room);

            return booking;
        }
    }
}
