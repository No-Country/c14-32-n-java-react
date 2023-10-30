package com.hotelapp.room.facade;

import com.hotelapp.room.dto.model.Room;

public interface CreateRoomFacade {
    Room saveRoom(Room room);
}
