package com.hotelapp.room.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.room.controller.validate.ValidateRoom;
import com.hotelapp.room.dto.request.CreateRoomRequest;
import com.hotelapp.room.dto.response.RoomDataResponse;
import com.hotelapp.room.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.*;
import static com.hotelapp.room.constants.RoomConstants.REQUEST_ROOM;

@RestController
@RequestMapping(REQUEST_ROOM)
public class RoomControllerImpl extends GenericRestController implements RoomController {

    private final CreateRoomService createRoomService;
    private final GetAllRoomService getAllRoomService;
    private final GetRoomByIdService getRoomByIdService;

    private final UpdateRoomService updateRoomService;
    private final DeleteRoomByIdService deleteRoomByIdService;
    private final ReserveRoomService reserveRoomService;

    public RoomControllerImpl(CreateRoomService createRoomService, GetAllRoomService getAllRoomService,
                              GetRoomByIdService getRoomByIdService, UpdateRoomService updateRoomService,DeleteRoomByIdService deleteRoomByIdService,
                              ReserveRoomService reserveRoomService) {
        this.createRoomService = createRoomService;
        this.getAllRoomService = getAllRoomService;
        this.getRoomByIdService = getRoomByIdService;
        this.updateRoomService = updateRoomService;
        this.deleteRoomByIdService = deleteRoomByIdService;
        this.reserveRoomService = reserveRoomService;
    }

    @Override
    public ResponseEntity<CustomResponse> save(CreateRoomRequest room, BindingResult bindingResult) {
        ValidateRoom.validateRoomRows(room, bindingResult);
        if(bindingResult.hasErrors()){
            return bad(room,bindingResult.getFieldError().getDefaultMessage(),REQUEST_ROOM);
        }
        return create(createRoomService.saveRoom(room),CREATED, REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllRooms(int numberPage) {
        return ok(getAllRoomService.getAllRoomsPaginator(numberPage),null,REQUEST_ROOM);
    }

    @Override
    public ResponseEntity<CustomResponse> getRoomById(Long id) {
        RoomDataResponse room = getRoomByIdService.getRoomById(id);
        if (room != null){
            return ok(room, null, REQUEST_ROOM);
        }
        return notFound(null,NOT_FOUND, REQUEST_ROOM);

    }

    @Override
    public ResponseEntity<CustomResponse> updateRoom(CreateRoomRequest room,BindingResult bindingResult) {
        ValidateRoom.validateRoomRows(room, bindingResult);
        if (bindingResult.hasErrors()){
            return bad(room,bindingResult.getFieldError().getDefaultMessage(),REQUEST_ROOM);
        }
        RoomDataResponse updateRoomData =  updateRoomService.updateRoom(room);
        if (updateRoomData != null){
            return ok(updateRoomData,null,REQUEST_ROOM);
        }
        return notFound(null,NOT_FOUND, REQUEST_ROOM);
    }


    @Override
    public ResponseEntity<CustomResponse> deleteRoomById(Long id) {
        deleteRoomByIdService.deleteRoomById(id);
        return ok(null,DELETED_SUCCESSFULLY, REQUEST_ROOM);
    }
}
