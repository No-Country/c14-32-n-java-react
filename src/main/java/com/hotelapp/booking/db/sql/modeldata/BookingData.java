package com.hotelapp.booking.db.sql.modeldata;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
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
    private Integer guestNumber;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomData room;


    public static final class BookingDataBuilder {
        private Long idBooking;
        private Date checkInDate;
        private Date checkOutDate;
        private Integer guestNumber;
        private BigDecimal price;
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

        public BookingDataBuilder guestNumber(Integer guestNumber) {
            this.guestNumber = guestNumber;
            return this;
        }

        public BookingDataBuilder price(BigDecimal price) {
            this.price = price;
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
            bookingData.setGuestNumber(guestNumber);
            bookingData.setPrice(price);
            bookingData.setRoom(room);
            return bookingData;
        }
    }
}
