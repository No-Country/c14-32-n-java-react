package com.hotelapp.booking.db.sql.mapper;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class BookingMapper {
    public Booking bookingDataToBooking(BookingData bookingData){
        Room room = roomDataToRoom(bookingData.getRoom());
        return new Booking.BookingBuilder()
                .checkInDate(bookingData.getCheckInDate())
                .checkOutDate(bookingData.getCheckOutDate())
                .guestNumber(bookingData.getGuestNumber())
                .idBooking(bookingData.getIdBooking())
                .price(bookingData.getPrice())
                .room(room)
                .build();
    }
    public Room roomDataToRoom(RoomData roomData){
        Category category = categoryDataToCategory(roomData.getRoomCategory());
        if(isNull(roomData)){
            return null;
        }
        return new Room.RoomBuilder()
                .idRoom(roomData.getIdRoom())
                .roomState(roomData.getRoomState())
                .roomNumber(roomData.getRoomNumber())
                .roomCategory(category)
                .build();
    }

    public RoomData roomToRoomData(Room room){
        CategoryData categoryData = categoryDataToCategory(room.getRoomCategory());

        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .roomCategory(categoryData)
                .build();
    }
    public BookingData bookingToBookingData(Booking booking){
        RoomData roomData = roomToRoomData(booking.getRoom());

        return new BookingData.BookingDataBuilder()
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .guestNumber(booking.getGuestNumber())
                .idBooking(booking.getIdBooking())
                .price(booking.getPrice())
                .room(roomData)
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




}
