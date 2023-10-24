package com.hotelapp.room.facade;

import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.response.RoomResponse;

public interface CreateRoomFacade {
    RoomResponse saveRoom(Room room);
}
