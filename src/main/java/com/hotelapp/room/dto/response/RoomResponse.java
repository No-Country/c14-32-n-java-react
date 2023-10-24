package com.hotelapp.room.dto.response;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.model.enums.RoomState;

public record RoomResponse(Long idRoom, Integer roomNumber, RoomState roomState, Category roomCategory) {
    public RoomResponse(Room room){
        this(room.getIdRoom(),room.getRoomNumber(), room.getRoomState(),
                room.getRoomCategory());
    }
}
