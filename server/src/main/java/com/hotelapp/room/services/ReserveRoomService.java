package com.hotelapp.room.services;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.response.BookingResponse;
import com.hotelapp.booking.facade.GetBookingByIdFacade;
import com.hotelapp.room.dto.mappers.ReserveRoomMapper;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.model.enums.RoomState;
import com.hotelapp.room.dto.request.GuestCheckRequest;
import com.hotelapp.room.dto.request.ReserveRoomRequest;
import com.hotelapp.room.dto.response.RoomDataResponse;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.stereotype.Service;


import static com.hotelapp.room.dto.model.enums.RoomState.RESERVED;

@Service
public class ReserveRoomService {
    private final CreateRoomFacade createRoomFacade;
    private final GetRoomByIdFacade getRoomByIdFacade;

    private final GetBookingByIdFacade getBookingByIdFacade;
    private final ReserveRoomMapper roomMapper;

    public ReserveRoomService(CreateRoomFacade createRoomFacade,
                              GetRoomByIdFacade getRoomByIdFacade,
                              ReserveRoomMapper roomMapper,
                              GetBookingByIdFacade getBookingByIdFacade) {
        this.createRoomFacade = createRoomFacade;
        this.getRoomByIdFacade = getRoomByIdFacade;
        this.roomMapper = roomMapper;
        this.getBookingByIdFacade = getBookingByIdFacade;
    }


    public RoomDataResponse roomStatusChange(GuestCheckRequest guestCheckRequest){
        Booking booking = getBookingByIdFacade.getBookingById(guestCheckRequest.idBooking());
        Room room = booking.getRoom();
        booking.setBookingState(BookingState.COMPLETED);
        room.setRoomState(RoomState.BUSY);
        return  new RoomDataResponse(createRoomFacade.saveRoom(room));
    }

}
