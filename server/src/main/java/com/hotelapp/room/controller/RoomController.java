package com.hotelapp.room.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.room.dto.request.CreateRoomRequest;
import com.hotelapp.room.dto.request.GuestCheckRequest;
import com.hotelapp.room.dto.request.ReserveRoomRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.hotelapp.commons.constants.GlobalApiConstant.ID_PARAM;

public interface RoomController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@Valid @RequestBody CreateRoomRequest createRoomRequest, BindingResult bindingResult);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllRooms(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getRoomById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> updateRoom(@RequestBody CreateRoomRequest createRoomRequest, BindingResult bindingResult);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteRoomById(@PathVariable Long id);

}
