package com.hotelapp.room.services;

import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.response.RoomDataResponse;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.stereotype.Service;


@Service
public class GetRoomByIdService {
    private final GetRoomByIdFacade getRoomByIdFacade;

    public GetRoomByIdService(GetRoomByIdFacade getRoomByIdFacade) {
        this.getRoomByIdFacade = getRoomByIdFacade;
    }

    public RoomDataResponse getRoomById(Long id){
        Room room = getRoomByIdFacade.getRoomById(id);
        if (room != null) return new RoomDataResponse(room);
        return null;
    }
}
