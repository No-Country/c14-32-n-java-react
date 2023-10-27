package com.hotelapp.room.services;

import com.hotelapp.room.facade.DeleteRoomByIdFacade;
import org.springframework.stereotype.Service;

@Service
public class DeleteRoomByIdService {
    private final DeleteRoomByIdFacade deleteRoomByIdFacade;


    public DeleteRoomByIdService(DeleteRoomByIdFacade deleteRoomByIdFacade) {
        this.deleteRoomByIdFacade = deleteRoomByIdFacade;
    }
    public void deleteRoomById(Long id){
        deleteRoomByIdFacade.deleteRoomById(id);
    }
}
