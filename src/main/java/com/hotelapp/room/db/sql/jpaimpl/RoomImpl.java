package com.hotelapp.room.db.sql.jpaimpl;

import com.hotelapp.room.db.sql.jparepository.RoomRepository;
import com.hotelapp.room.db.sql.mapper.RoomMapper;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.facade.DeleteRoomByIdFacade;
import com.hotelapp.room.facade.GetAllRoomFacade;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoomImpl implements CreateRoomFacade, GetAllRoomFacade, GetRoomByIdFacade, DeleteRoomByIdFacade {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    public RoomImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Room saveRoom(Room room) {
        return roomMapper.roomDataToRoom(
                roomRepository.save(roomMapper.roomToRoomData(room)));
    }

    @Override
    public Page<Room> getAllRoomsPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        Page<RoomData> roomData = roomRepository.findAll(page);
        return roomData.map(roomMapper::roomDataToRoom);
    }

    @Override
    public Room getRoomById(Long id) {
        Optional<RoomData> roomFinded = roomRepository.findById(id);
        if(roomFinded.isPresent()){
            return roomMapper.roomDataToRoom(roomFinded.get());
        }
        return null;
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}
