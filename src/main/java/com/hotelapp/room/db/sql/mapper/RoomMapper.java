package com.hotelapp.room.db.sql.mapper;

import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public Room roomDataToRoom(RoomData roomData){
        return new Room.RoomBuilder()
                .idRoom(roomData.getIdRoom())
                .roomState(roomData.getRoomState())
                .roomNumber(roomData.getRoomNumber())
                .build();
    }
    public RoomData roomToRoomData(Room room){
        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .build();
    }
}
