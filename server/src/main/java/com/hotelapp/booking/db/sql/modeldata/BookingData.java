package com.hotelapp.booking.db.sql.modeldata;

import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.model.enums.PaymentType;
import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;
    private Date checkInDate;
    private Date checkOutDate;
    private Date date;
    private BookingState bookingState;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerData customer;
    private BigDecimal price;

    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomData room;



    public static final class BookingDataBuilder {
        private Long idBooking;
        private Date checkInDate;
        private Date checkOutDate;
        private Date date;
        private BookingState bookingState;
        private CustomerData customer;
        private BigDecimal price;
        private PaymentType paymentType;
        private RoomData room;



        public BookingDataBuilder() {
        }

        public static BookingDataBuilder aBookingData() {
            return new BookingDataBuilder();
        }

        public BookingDataBuilder idBooking(Long idBooking) {
            this.idBooking = idBooking;
            return this;
        }

        public BookingDataBuilder checkInDate(Date checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public BookingDataBuilder checkOutDate(Date checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public BookingDataBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public BookingDataBuilder bookingState(BookingState bookingState) {
            this.bookingState = bookingState;
            return this;
        }

        public BookingDataBuilder customer(CustomerData customer) {
            this.customer = customer;
            return this;
        }

        public BookingDataBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookingDataBuilder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public BookingDataBuilder room(RoomData room) {
            this.room = room;
            return this;
        }



        public BookingData build() {
            BookingData bookingData = new BookingData();
            bookingData.setIdBooking(idBooking);
            bookingData.setCheckInDate(checkInDate);
            bookingData.setCheckOutDate(checkOutDate);
            bookingData.setDate(date);
            bookingData.setBookingState(bookingState);
            bookingData.setCustomer(customer);
            bookingData.setPrice(price);
            bookingData.setPaymentType(paymentType);
            bookingData.setRoom(room);
            return bookingData;
        }
    }
}
