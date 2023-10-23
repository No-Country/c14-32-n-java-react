package com.hotelapp.room.services;

import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.stereotype.Service;

@Service
public class GetRoomByIdService {
    private final GetRoomByIdFacade getRoomByIdFacade;


    public GetRoomByIdService(GetRoomByIdFacade getRoomByIdFacade) {
        this.getRoomByIdFacade = getRoomByIdFacade;
    }

    public Room getRoomById(Long id){
        return getRoomByIdFacade.getRoomById(id);
    }
}
