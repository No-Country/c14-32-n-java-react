package com.hotelapp.room.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.services.CreateRoomService;
import com.hotelapp.room.services.GetAllRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;
import static com.hotelapp.room.constants.RoomConstants.REQUEST_ROOM;

@RestController
@RequestMapping(REQUEST_ROOM)
public class RoomControllerImpl extends GenericRestController implements RoomController {

    private final CreateRoomService createRoomService;
    private final GetAllRoomService getAllRoomService;

    public RoomControllerImpl(CreateRoomService createRoomService, GetAllRoomService getAllRoomService) {
        this.createRoomService = createRoomService;
        this.getAllRoomService = getAllRoomService;
    }

    @Override
    public ResponseEntity<CustomResponse> save(Room room) {
        return create(createRoomService.saveRoom(room),CREATED, REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllRooms(int numberPage) {
        return ok(getAllRoomService.getAllRoomsPaginator(numberPage),null,REQUEST_ROOM);
    }
}
