package com.hotelapp.room.services;

import com.hotelapp.room.dto.mappers.CreateRoomRequestMapper;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.CreateRoomRequest;
import com.hotelapp.room.dto.response.RoomDataResponse;
import com.hotelapp.room.facade.UpdateRoomFacade;
import org.springframework.stereotype.Service;

@Service
public class UpdateRoomService {

    private final UpdateRoomFacade updateRoomFacade;
    private final CreateRoomRequestMapper createRoomRequestMapper;


    public UpdateRoomService(UpdateRoomFacade updateRoomFacade,
                             CreateRoomRequestMapper createRoomRequestMapper){
        this.updateRoomFacade = updateRoomFacade;
        this.createRoomRequestMapper = createRoomRequestMapper;
    }

    public RoomDataResponse updateRoom(CreateRoomRequest createRoomRequest){
        Room room = updateRoomFacade.updateRoom(createRoomRequestMapper
                .createRoomRequestToRoom(createRoomRequest));
        if (room != null) return new RoomDataResponse(room);
        return null;
    }

}
