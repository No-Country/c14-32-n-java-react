package com.hotelapp.room.services.helper;

import com.hotelapp.categoryRoom.facade.GetCategoryByIdFacade;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Service;

@Service
public class CreateRoomHelper {
    private final GetCategoryByIdFacade getCategoryByIdFacade;

    public CreateRoomHelper(GetCategoryByIdFacade getCategoryByIdFacade){
        this.getCategoryByIdFacade = getCategoryByIdFacade;
    }

    public void setRoomCategory(Room room){
        room.setRoomCategory(getCategoryByIdFacade.getCategoryById(room.getRoomCategory().getIdCategory()));
    }

}
