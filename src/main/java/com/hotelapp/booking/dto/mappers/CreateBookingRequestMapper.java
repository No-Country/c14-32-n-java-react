package com.hotelapp.booking.dto.mappers;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.dto.request.CreateBookingRequest;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.room.dto.model.Room;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
public class CreateBookingRequestMapper {

    public Booking createBookingRequestToBooking(CreateBookingRequest createBookingRequest){
        Long id = null;
        if(!isNull(createBookingRequest.getIdBooking())){
            id = createBookingRequest.getIdBooking();
        }
        Room room = new Room.RoomBuilder().idRoom(createBookingRequest.getIdRoom()).build();

        Customer customer = new Customer.CustomerBuilder().idCustomer(createBookingRequest.getIdCustomer()).build();
        return new Booking.BookingBuilder()
                .price(new BigDecimal(50.0))
                .customer(customer)
                .checkOutDate(createBookingRequest.getCheckOutDate())
                .checkInDate(createBookingRequest.getCheckInDate())
                .idBooking(id)
                .room(room)
                .build();


    }

}
