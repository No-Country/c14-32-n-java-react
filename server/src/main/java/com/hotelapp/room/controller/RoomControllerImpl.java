package com.hotelapp.room.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.room.controller.validate.ValidateRoom;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.ReserveRoomRequest;
import com.hotelapp.room.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;
import static com.hotelapp.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;
import static com.hotelapp.room.constants.RoomConstants.REQUEST_ROOM;

@RestController
@RequestMapping(REQUEST_ROOM)
public class RoomControllerImpl extends GenericRestController implements RoomController {

    private final CreateRoomService createRoomService;
    private final GetAllRoomService getAllRoomService;
    private final GetRoomByIdService getRoomByIdService;
    private final DeleteRoomByIdService deleteRoomByIdService;

    private final ReserveRoomService reserveRoomService;

    public RoomControllerImpl(CreateRoomService createRoomService, GetAllRoomService getAllRoomService, GetRoomByIdService getRoomByIdService, DeleteRoomByIdService deleteRoomByIdService, ReserveRoomService reserveRoomService) {
        this.createRoomService = createRoomService;
        this.getAllRoomService = getAllRoomService;
        this.getRoomByIdService = getRoomByIdService;
        this.deleteRoomByIdService = deleteRoomByIdService;
        this.reserveRoomService = reserveRoomService;
    }

    @Override
    public ResponseEntity<CustomResponse> save(Room room, BindingResult bindingResult) {
        ValidateRoom.validateRoomRows(room, bindingResult);
        if(bindingResult.hasErrors()){
            return bad(room,bindingResult.getFieldError().getDefaultMessage(),REQUEST_ROOM);
        }
        return create(createRoomService.saveRoom(room),CREATED, REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> reserveRoom(ReserveRoomRequest reserveRoomRequest) {
        return ok(reserveRoomService.reserveRoom(reserveRoomRequest),"ok",REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllRooms(int numberPage) {
        return ok(getAllRoomService.getAllRoomsPaginator(numberPage),null,REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> getRoomById(Long id) {
        return ok(getRoomByIdService.getRoomById(id), null, REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteRoomById(Long id) {
        deleteRoomByIdService.deleteRoomById(id);
        return ok(null,DELETED_SUCCESSFULLY, REQUEST_ROOM);
    }
}
