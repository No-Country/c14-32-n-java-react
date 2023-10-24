package com.hotelapp.room.facade;

import com.hotelapp.room.dto.response.RoomResponse;
import org.springframework.data.domain.Page;

public interface GetAllRoomFacade {
    Page<RoomResponse> getAllRoomsPaginator(int numberPage);
}
