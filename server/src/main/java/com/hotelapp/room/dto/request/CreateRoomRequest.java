package com.hotelapp.room.dto.request;

import com.hotelapp.room.dto.model.enums.RoomState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateRoomRequest {

    private Long idRoom;
    private Integer roomNumber;
    private RoomState roomState;
    private Long roomCategory;
}