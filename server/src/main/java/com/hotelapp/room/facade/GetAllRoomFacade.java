package com.hotelapp.room.facade;

import com.hotelapp.room.dto.model.Room;
import org.springframework.data.domain.Page;

public interface GetAllRoomFacade {
    Page<Room> getAllRoomsPaginator(int numberPage);
}
