package com.hotelapp.room.db.sql.jpaimpl;

import com.hotelapp.room.db.sql.jparepository.RoomRepository;
import com.hotelapp.room.db.sql.mapper.RoomMapper;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.response.RoomResponse;
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
    public RoomResponse saveRoom(Room room) {
         Room roomSaved = roomMapper.roomDataToRoom(roomRepository.save(roomMapper.roomToRoomData(room)));
         return roomMapper.roomToRoomResponse(roomSaved);
    }

    @Override
    public Page<RoomResponse> getAllRoomsPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        Page<Room> room = roomRepository.findAll(page).map(roomMapper::roomDataToRoom);
        return room.map(roomMapper::roomToRoomResponse);
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
