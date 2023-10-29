package com.hotelapp.booking.db.sql.mapper;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import com.hotelapp.room.db.sql.mapper.RoomMapper;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;


@Component
public class BookingMapper {

    private final CustomerMapper customerMapper;
    private final RoomMapper roomMapper;

    public BookingMapper(CustomerMapper customerMapper,RoomMapper roomMapper){
        this.customerMapper = customerMapper;
        this.roomMapper = roomMapper;
    }


    public Booking bookingDataToBooking(BookingData bookingData){
        Room room = roomMapper.roomDataToRoom(bookingData.getRoom());
        Customer customer = customerMapper.customerDataToCustomer(bookingData.getCustomer());

        return new Booking.BookingBuilder()
                .checkInDate(bookingData.getCheckInDate())
                .checkOutDate(bookingData.getCheckOutDate())
                .date(bookingData.getDate())
                .bookingState(bookingData.getBookingState())
                .customer(customer)
                .idBooking(bookingData.getIdBooking())
                .price(bookingData.getPrice())
                .paymentType(bookingData.getPaymentType())
                .room(room)
                .build();
    }

    public BookingData bookingToBookingData(Booking booking){

        RoomData roomData = roomMapper.roomToRoomData(booking.getRoom());
        CustomerData customerData = customerMapper.customerToCustomerData(booking.getCustomer());

        return new BookingData.BookingDataBuilder()
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .date(booking.getDate())
                .bookingState(booking.getBookingState())
                .customer(customerData)
                .idBooking(booking.getIdBooking())
                .price(booking.getPrice())
                .paymentType(booking.getPaymentType())
                .room(roomData)
                .build();

    }
}
