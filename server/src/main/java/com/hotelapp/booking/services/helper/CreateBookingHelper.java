package com.hotelapp.booking.services.helper;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.categoryRoom.facade.GetCategoryByIdFacade;
import com.hotelapp.customer.facade.GetCustomerByIdFacade;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.model.enums.RoomState;
import com.hotelapp.room.facade.CreateRoomFacade;
import com.hotelapp.room.facade.GetRoomByIdFacade;
import com.hotelapp.room.services.ReserveRoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CreateBookingHelper {
    private final GetRoomByIdFacade getRoomByIdFacade;
    private final GetCustomerByIdFacade getCustomerByIdFacade;
    private final GetCategoryByIdFacade getCategoryByIdFacade;
    private final ReserveRoomService reserveRoomService;
    private final BookingCalculatorHelper bookingCalculatorHelper;


    public CreateBookingHelper(GetRoomByIdFacade getRoomByIdFacade,GetCustomerByIdFacade getCustomerByIdFacade,
                               ReserveRoomService reserveRoomService,GetCategoryByIdFacade getCategoryByIdFacade,
                               BookingCalculatorHelper bookingCalculatorHelper) {
        this.getRoomByIdFacade = getRoomByIdFacade;
        this.getCustomerByIdFacade = getCustomerByIdFacade;
        this.reserveRoomService = reserveRoomService;
        this.getCategoryByIdFacade = getCategoryByIdFacade;
        this.bookingCalculatorHelper = bookingCalculatorHelper;
    }

    public void setBookingRoom(Booking booking){
        Room room = getRoomByIdFacade.getRoomById(booking.getRoom().getIdRoom());
        LocalDate checkIn = booking.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (checkIn.equals(LocalDate.now())) {
            room.setRoomState(RoomState.RESERVED);//current reserved status of the day
        }
        booking.setRoom(room);
    }

    public void setBookingCustomer(Booking booking){
        booking.setCustomer(getCustomerByIdFacade.getCustomerById(booking.getCustomer().getIdCustomer()));
    }

    public void setBooking(Booking booking){
        booking.setPrice(bookingCalculatorHelper.
                bookingCalculator(booking.getCheckInDate(),booking.getCheckOutDate(), booking.getRoom().
                        getRoomCategory().getBasePrice()));// TOTAL PRICE
        booking.setBookingState(BookingState.CONFIRMED);// BOOKING STATUS
        booking.setDate(new Date());
    }
}
