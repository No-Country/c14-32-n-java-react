package com.hotelapp.room.db.sql.mapper;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.categoryRoom.db.sql.mapper.CategoryMapper;
import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.response.RoomResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
                .date(bookingData.getDate())
                .bookingState(bookingData.getBookingState())
                .customer(customerMapper
                        .customerDataToCustomer(bookingData.getCustomer()))
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
                .date(booking.getDate())
                .bookingState(booking.getBookingState())
                .customer(customerMapper
                        .customerToCustomerData(booking.getCustomer()))
                .build()
        ).collect(Collectors.toList());
    }
    public RoomData roomToRoomData(Room room){
        CategoryData categoryData = categoryMapper.categoryToCategoryData(room.getRoomCategory());
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



    public void chargeListBookingData(List<BookingData> listBookingData, Room room){
        if(!isNull(room.getListBooking())){
            List<Booking> listBooking = room.getListBooking();
            listBookingData =listToBookingData(listBooking);
            if (!listBookingData.isEmpty()) {
                listBookingData.get(0).setRoom(roomWithoutBookingListToRoomData(room));
            }
        }
    }

    public RoomData roomWithoutBookingListToRoomData(Room room){
        CategoryData categoryData = categoryMapper.categoryToCategoryData(room.getRoomCategory());
        return new RoomData.RoomDataBuilder()
                .idRoom(room.getIdRoom())
                .roomNumber(room.getRoomNumber())
                .roomState(room.getRoomState())
                .roomCategory(categoryData)
                .build();
    }


    public RoomResponse roomToRoomResponse(Room room){
        return new RoomResponse(room);
    }

    public Room RoomResponseToRoom(RoomResponse roomResponse){
        return new Room.RoomBuilder()
                .idRoom(roomResponse.idRoom())
                .roomNumber(roomResponse.roomNumber())
                .roomState(roomResponse.roomState())
                .roomCategory(roomResponse.roomCategory())
                .build();
    }



}
