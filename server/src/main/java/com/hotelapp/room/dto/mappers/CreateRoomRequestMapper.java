package com.hotelapp.room.dto.mappers;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.CreateRoomRequest;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CreateRoomRequestMapper {

    public Room createRoomRequestToRoom(CreateRoomRequest createRoomRequest){
        Long id = null;
        if(!isNull(createRoomRequest.getIdRoom())){
            id = createRoomRequest.getIdRoom();
        }
        Category category = new Category.CategoryBuilder().idCategory(createRoomRequest.getRoomCategory()).build();
        return  new Room.RoomBuilder()
                .idRoom(id)
                .roomNumber(createRoomRequest.getRoomNumber())
                .roomState(createRoomRequest.getRoomState())
                .roomCategory(category)
                .build();
    }
}
