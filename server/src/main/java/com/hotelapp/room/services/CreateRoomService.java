package com.hotelapp.room.services;

import com.hotelapp.room.dto.mappers.CreateRoomRequestMapper;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.CreateRoomRequest;
import com.hotelapp.room.dto.response.RoomDataResponse;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.services.helper.CreateRoomHelper;
import org.springframework.stereotype.Service;
@Service
public class CreateRoomService {
   private final CreateRoomFacade createRoomFacade;
   private final CreateRoomRequestMapper createRoomRequestMapper;
   private final CreateRoomHelper createRoomHelper;

    public CreateRoomService(CreateRoomFacade createRoomFacade, CreateRoomRequestMapper createRoomRequestMapper,
                             CreateRoomHelper createRoomHelper) {
        this.createRoomFacade = createRoomFacade;
        this.createRoomRequestMapper = createRoomRequestMapper;
        this.createRoomHelper = createRoomHelper;
    }


    public RoomDataResponse saveRoom(CreateRoomRequest createRoomRequest){
        Room room = createRoomRequestMapper.createRoomRequestToRoom(createRoomRequest);
        createRoomHelper.setRoomCategory(room);
        return new RoomDataResponse(createRoomFacade.saveRoom(room));
    }
}
