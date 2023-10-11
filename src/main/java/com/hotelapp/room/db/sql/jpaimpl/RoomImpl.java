package com.hotelapp.room.db.sql.jpaimpl;

import com.hotelapp.room.db.sql.jparepository.RoomRepository;
import com.hotelapp.room.db.sql.mapper.RoomMapper;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.facade.GetAllRoomFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class RoomImpl implements CreateRoomFacade, GetAllRoomFacade {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    public RoomImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Room saveRoom(Room room) {
        return roomMapper.roomDataToRoom(roomRepository
                .save(roomMapper.roomToRoomData(room))) ;
    }

    @Override
    public Page<Room> getAllRoomsPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        Page<RoomData> roomData = roomRepository.findAll(page);
        return roomData.map(roomMapper::roomDataToRoom);
    }
}
