package com.hotelapp.room.dto.model;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.room.dto.model.enums.RoomState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Long idRoom;
    private Integer roomNumber;
    private RoomState roomState;
    private Category roomCategory;

    public static final class RoomBuilder {
        private Long idRoom;
        private Integer roomNumber;
        private RoomState roomState;
        private Category roomCategory;

        public RoomBuilder() {
        }

        public static RoomBuilder aRoom() {
            return new RoomBuilder();
        }

        public RoomBuilder idRoom(Long idRoom) {
            this.idRoom = idRoom;
            return this;
        }

        public RoomBuilder roomNumber(Integer roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public RoomBuilder roomState(RoomState roomState) {
            this.roomState = roomState;
            return this;
        }

        public RoomBuilder roomCategory(Category category) {
            this.roomCategory = category;
            return this;
        }


        public Room build() {
            Room room = new Room();
            room.setIdRoom(idRoom);
            room.setRoomNumber(roomNumber);
            room.setRoomState(roomState);
            room.setRoomCategory(roomCategory);
            return room;
        }
    }
}
