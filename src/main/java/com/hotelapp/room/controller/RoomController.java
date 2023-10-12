package com.hotelapp.room.controller;

import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.room.dto.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoomController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@RequestBody Room room);

    @GetMapping("/getPage/{numberPage}")
    ResponseEntity<CustomResponse> getAllRooms(@PathVariable int numberPage);

    @GetMapping("/{id}")
    ResponseEntity<CustomResponse> getRoomById(@PathVariable Long id);

}
