package com.hotelapp.room.db.sql.modeldata;

import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.room.dto.model.enums.RoomState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    @Column(unique = true)
    private Integer roomNumber;

    private RoomState roomState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryData roomCategory;


    public static final class RoomDataBuilder {
        private Long idRoom;
        private Integer roomNumber;
        private RoomState roomState;
        private CategoryData roomCategory;

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

        public RoomDataBuilder roomCategory(CategoryData roomCategory) {
            this.roomCategory = roomCategory;
            return this;
        }

        public RoomData build() {
            RoomData roomData = new RoomData();
            roomData.setIdRoom(idRoom);
            roomData.setRoomNumber(roomNumber);
            roomData.setRoomState(roomState);
            roomData.setRoomCategory(roomCategory);
            return roomData;
        }
    }
}
