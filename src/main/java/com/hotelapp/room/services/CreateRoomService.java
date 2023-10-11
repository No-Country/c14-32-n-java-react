package com.hotelapp.room.services;

import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.CreateRoomFacade;
import org.springframework.stereotype.Service;
@Service
public class CreateRoomService {
   private final CreateRoomFacade createRoomFacade;
    public CreateRoomService(CreateRoomFacade createRoomFacade) {
        this.createRoomFacade = createRoomFacade;
    }

    public Room saveRoom(Room room) {

        return createRoomFacade.saveRoom(room);
    }
}
