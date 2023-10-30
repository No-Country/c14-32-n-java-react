package com.hotelapp.room.services;

import com.hotelapp.booking.dto.response.BookingResponse;
import com.hotelapp.room.dto.mappers.ReserveRoomMapper;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.ReserveRoomRequest;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.stereotype.Service;


import static com.hotelapp.room.dto.model.enums.RoomState.RESERVED;

@Service
public class ReserveRoomService {
    private final CreateRoomFacade createRoomFacade;
    private final GetRoomByIdFacade getRoomByIdFacade;
    private final ReserveRoomMapper roomMapper;

    public ReserveRoomService(CreateRoomFacade createRoomFacade, GetRoomByIdFacade getRoomByIdFacade, ReserveRoomMapper roomMapper) {
        this.createRoomFacade = createRoomFacade;
        this.getRoomByIdFacade = getRoomByIdFacade;
        this.roomMapper = roomMapper;
    }


    public BookingResponse reserveRoom(ReserveRoomRequest reserveRoomRequest){
        Room room = getRoomByIdFacade.getRoomById(reserveRoomRequest.getIdRoom());
        room.setRoomState(RESERVED);
        createRoomFacade.saveRoom(room);
       return roomMapper.RoomToReserveRoomRequest(room, reserveRoomRequest.getBooking());
    }

}
