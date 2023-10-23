package com.hotelapp.room.db.sql.modeldata;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.room.dto.model.enums.RoomState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;
    private Integer roomNumber;
    private RoomState roomState;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingData> listBooking;

    public static final class RoomDataBuilder {
        private Long idRoom;
        private Integer roomNumber;
        private RoomState roomState;
        private List<BookingData> listBooking;

        public RoomDataBuilder() {
        }

        public static RoomDataBuilder aRoomData() {
            return new RoomDataBuilder();
        }

        public RoomDataBuilder idRoom(Long idRoom) {
            this.idRoom = idRoom;
            return this;
        }

        public RoomDataBuilder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public RoomDataBuilder roomState(RoomState roomState) {
            this.roomState = roomState;
            return this;
        }

        public RoomDataBuilder listBooking(List<BookingData> listBooking) {
            this.listBooking = listBooking;
            return this;
        }

        public RoomData build() {
            RoomData roomData = new RoomData();
            roomData.setIdRoom(idRoom);
            roomData.setRoomNumber(roomNumber);
            roomData.setRoomState(roomState);
            roomData.setListBooking(listBooking);
            return roomData;
        }
    }
}
