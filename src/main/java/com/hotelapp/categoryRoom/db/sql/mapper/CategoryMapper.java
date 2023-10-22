package com.hotelapp.categoryRoom.db.sql.mapper;

import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class CategoryMapper {


    /*
     CATEGORY_DATA -> CATEGORY
    */
    public Category categoryDataToCategory(CategoryData categoryData){
       /* List<RoomData> listRooms = null;
        if(!isNull(categoryData.getListRooms())){
            listRooms = categoryData.getListRooms();
        }*/
        return new Category.CategoryBuilder()
                .idCategory(categoryData.getIdCategory())
                .categoryName(categoryData.getCategoryName())
                .categoryDescription(categoryData.getCategoryDescription())
                .basePrice(categoryData.getBasePrice())
               // .listRooms(listDataToRoom(listRooms))
                .build();
    }

    /*
    CATEGORY -> CATEGORY_DATA
    */
    public CategoryData categoryToCategoryData(Category category){
       // List<RoomData> listRoomData = null;
       // chargeListRoomData(listRoomData, category);
        return new CategoryData.CategoryDataBuilder()
                .idCategory(category.getIdCategory())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .basePrice(category.getBasePrice())
                //.listRooms(listRoomData)
                .build();
    }


    /*
    LIST_DATA -> ROOM
     */
    public List<Room> listDataToRoom(List<RoomData> listData){

        if(isNull(listData)){
            return null;
        }
        return listData.stream().map(roomData -> new Room.RoomBuilder()
                .idRoom(roomData.getIdRoom())
                .roomNumber(roomData.getRoomNumber())
                .roomState(roomData.getRoomState())
                .roomCategory(categoryDataToCategory(roomData.getRoomCategory()))
                .build()
        ).collect(Collectors.toList());
    }

    /*
    LIST -> ROOM_DATA
     */
    public List<RoomData> listToRoomData(List<Room> list){

        if(isNull(list)){
            return null;
        }
        return list.stream().map(room -> new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .roomCategory(categoryToCategoryData(room.getRoomCategory()))
                .build()
        ).collect(Collectors.toList());
    }



    /*public void chargeListRoomData(List<RoomData> listRoomData, Category category){
        if(!isNull(category.getListRooms())){
            List<Room> listRooms = category.getListRooms();
            listRoomData = listToRoomData(listRooms);
            listRoomData.get(0).setRoomCategory(categoryWithoutRoomListToCategoryData(category));
        }
    }*/

    public CategoryData categoryWithoutRoomListToCategoryData(Category category){
        return new CategoryData.CategoryDataBuilder()
                .idCategory(category.getIdCategory())
                .categoryDescription(category.getCategoryDescription())
                .basePrice(category.getBasePrice())
                .build();
    }

}

