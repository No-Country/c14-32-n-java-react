package com.hotelapp.room.services;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.response.BookingResponse;
import com.hotelapp.room.dto.mappers.ReserveRoomMapper;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.request.ReserveRoomRequest;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

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
        addNewBookingToRoomObject(reserveRoomRequest, room);
       createRoomFacade.saveRoom(room);
       return roomMapper.RoomToReserveRoomRequest(room, reserveRoomRequest.getBooking());
    }

    private void addNewBookingToRoomObject(ReserveRoomRequest reserveRoomRequest, Room room) {
        if(!isNull(room.getListBooking())){
            room.getListBooking().add(reserveRoomRequest.getBooking());
        }else{
            List<Booking> newListBooking = new ArrayList<>();
            newListBooking.add(reserveRoomRequest.getBooking());
            room.setListBooking(newListBooking);
        }
    }
}
