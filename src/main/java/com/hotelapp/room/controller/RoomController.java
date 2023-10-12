package com.hotelapp.room.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.room.dto.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.hotelapp.commons.constants.GlobalApiConstant.ID_PARAM;

public interface RoomController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody Room room, BindingResult bindingResult);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllRooms(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getRoomById(@PathVariable Long id);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteRoomById(@PathVariable Long id);

}
