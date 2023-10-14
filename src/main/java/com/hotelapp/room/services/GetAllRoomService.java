package com.hotelapp.room.services;

import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.GetAllRoomFacade;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetAllRoomService {
    private final GetAllRoomFacade getAllRoomFacade;

    public GetAllRoomService(GetAllRoomFacade getAllRoomFacade) {
        this.getAllRoomFacade = getAllRoomFacade;
    }

    public Page<Room> getAllRoomsPaginator(int numberPage){
        return getAllRoomFacade.getAllRoomsPaginator(numberPage);
    }
}