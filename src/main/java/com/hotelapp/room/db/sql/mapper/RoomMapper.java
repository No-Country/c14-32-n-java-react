package com.hotelapp.room.db.sql.mapper;
import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class RoomMapper {
    public Room roomDataToRoom(RoomData roomData){
        Category category = categoryDataToCategory(roomData.getRoomCategory());
        List<BookingData> listBooking = null;
        if(!isNull(roomData.getListBooking())){
            listBooking = roomData.getListBooking();
        }

        return new Room.RoomBuilder()
                .idRoom(roomData.getIdRoom())
                .roomState(roomData.getRoomState())
                .roomNumber(roomData.getRoomNumber())
                .roomCategory(category)
                .listBooking(listDataToBooking(listBooking))
                .build();
    }
    public List<Booking> listDataToBooking(List<BookingData> listData){
        if(isNull(listData)){
            return null;
        }
      return listData.stream().map(bookingData -> new Booking.BookingBuilder()
                .idBooking(bookingData.getIdBooking())
                .price(bookingData.getPrice())
                .checkInDate(bookingData.getCheckInDate())
                .checkOutDate(bookingData.getCheckOutDate())
                .build()
        ).collect(Collectors.toList());
    }
    public List<BookingData> listToBookingData(List<Booking> list){
        if(isNull(list)){
            return null;
        }
        return list.stream().map(booking -> new BookingData.BookingDataBuilder()
                .idBooking(booking.getIdBooking())
                .price(booking.getPrice())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .build()
        ).collect(Collectors.toList());
    }
    public RoomData roomToRoomData(Room room){
        CategoryData categoryData = categoryDataToCategory(room.getRoomCategory());
        List<BookingData> listBookingData = null;
        chargeListBookingData(listBookingData, room);
        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .roomCategory(categoryData)
                .listBooking(listBookingData)
                .build();
    }



    public Category categoryDataToCategory(CategoryData categoryData){
        if(isNull(categoryData)){
            return null;
        }
        return new Category.CategoryBuilder()
                .idCategory(categoryData.getIdCategory())
                .categoryName(categoryData.getCategoryName())
                .categoryDescription(categoryData.getCategoryDescription())
                .basePrice(categoryData.getBasePrice())
                .build();
    }

    public CategoryData categoryDataToCategory(Category category){
        if(isNull(category)){
            return null;
        }
        return new CategoryData.CategoryDataBuilder()
                .idCategory(category.getIdCategory())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .basePrice(category.getBasePrice())
                .build();
    }





    public void chargeListBookingData(List<BookingData> listBookingData, Room room){
        if(!isNull(room.getListBooking())){
            List<Booking> listBooking = room.getListBooking();
            listBookingData =listToBookingData(listBooking);
            listBookingData.get(0).setRoom(roomWithoutBookingListToRoomData(room));
        }
    }

    public RoomData roomWithoutBookingListToRoomData(Room room){
        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .build();
    }


}
