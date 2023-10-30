package com.hotelapp.room.db.sql.jpaimpl;

import com.hotelapp.room.db.sql.jparepository.RoomRepository;
import com.hotelapp.room.db.sql.mapper.RoomMapper;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.facade.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoomImpl implements CreateRoomFacade, GetAllRoomFacade,
        GetRoomByIdFacade,UpdateRoomFacade ,DeleteRoomByIdFacade {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    public RoomImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Room saveRoom(Room room) {
        RoomData roomData = roomRepository.save(roomMapper.roomToRoomData(room));
        return roomMapper.roomDataToRoom(roomData);
    }

    @Override
    public Page<Room> getAllRoomsPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        return roomRepository.findAll(page).map(roomMapper::roomDataToRoom);
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
    public Room updateRoom(Room room) {
        Optional<RoomData> roomDataOptional = roomRepository.findById(room.getIdRoom());
        if (roomDataOptional.isPresent()){
            RoomData roomData = roomRepository.save(roomMapper.roomToRoomData(room));
            return roomMapper.roomDataToRoom(roomData);
        }
        return null;
    }



    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}
