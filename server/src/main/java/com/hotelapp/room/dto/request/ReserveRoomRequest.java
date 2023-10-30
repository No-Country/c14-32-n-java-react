package com.hotelapp.room.dto.request;

import com.hotelapp.booking.dto.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRoomRequest {
    private Long idRoom;
    private Booking booking;


    public static final class ReserveRoomBuilder {
        private Long idRoom;
        private Booking booking;

        public ReserveRoomBuilder() {
        }



        public static ReserveRoomBuilder aBookRoomRequest() {
            return new ReserveRoomBuilder();
        }

        public ReserveRoomBuilder idRoom(Long idRoom) {
            this.idRoom = idRoom;
            return this;
        }

        public ReserveRoomBuilder booking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public ReserveRoomRequest build() {
            ReserveRoomRequest bookRoomRequest = new ReserveRoomRequest();
            bookRoomRequest.setIdRoom(idRoom);
            bookRoomRequest.setBooking(booking);
            return bookRoomRequest;
        }
    }
}
