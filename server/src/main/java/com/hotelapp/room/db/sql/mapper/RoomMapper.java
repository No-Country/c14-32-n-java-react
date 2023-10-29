package com.hotelapp.room.db.sql.mapper;

import com.hotelapp.categoryRoom.db.sql.mapper.CategoryMapper;
import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    private final CategoryMapper categoryMapper;
    private final CustomerMapper customerMapper;

    public RoomMapper(CategoryMapper categoryMapper,CustomerMapper customerMapper){
        this.categoryMapper = categoryMapper;
        this.customerMapper = customerMapper;
    }

    public Room roomDataToRoom(RoomData roomData){
        Category category = categoryMapper.categoryDataToCategory(roomData.getRoomCategory());
        return new Room.RoomBuilder()
                .idRoom(roomData.getIdRoom())
                .roomState(roomData.getRoomState())
                .roomNumber(roomData.getRoomNumber())
                .roomCategory(category)
                .build();
    }
    public RoomData roomToRoomData(Room room){
        CategoryData categoryData = categoryMapper.categoryToCategoryData(room.getRoomCategory());
        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .roomCategory(categoryData)
                .build();
    }
}
