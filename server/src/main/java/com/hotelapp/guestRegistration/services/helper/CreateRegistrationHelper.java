package com.hotelapp.guestRegistration.services.helper;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.booking.dto.model.enums.BookingState;
import com.hotelapp.booking.facade.CreateBookingFacade;
import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.guestRegistration.facade.GetGuestRegistrationByIdFacade;
import com.hotelapp.infra.error.IntegrityValidation;
import com.hotelapp.room.dto.model.Room;
import com.hotelapp.room.dto.model.enums.RoomState;
import com.hotelapp.room.facade.CreateRoomFacade;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateRegistrationHelper {

    private final CreateRoomFacade createRoomFacade;
    private final CreateBookingFacade createBookingFacade;
    private final GetGuestRegistrationByIdFacade getGuestRegistrationByIdFacade;
    private final GuestRegistrationCalculatorHelper guestRegistrationCalculatorHelper;

    public CreateRegistrationHelper(CreateRoomFacade createRoomFacade,
                                    CreateBookingFacade createBookingFacade,
                                    GuestRegistrationCalculatorHelper guestRegistrationCalculatorHelper,
                                    GetGuestRegistrationByIdFacade getGuestRegistrationByIdFacade){
        this.createRoomFacade = createRoomFacade;
        this.createBookingFacade = createBookingFacade;
        this.guestRegistrationCalculatorHelper = guestRegistrationCalculatorHelper;
        this.getGuestRegistrationByIdFacade = getGuestRegistrationByIdFacade;
    }

    public void setRegistrationBooking(Booking booking){
        if (booking.getBookingState().equals(BookingState.COMPLETED)){
            throw new IntegrityValidation("not allowed, registration is complete");
        }
        booking.setBookingState(BookingState.COMPLETED);
        createBookingFacade.saveBooking(booking);
    }

    public void setRegistrationRoom(Room room){
        room.setRoomState(
                room.getRoomState().equals(RoomState.RESERVED) ||
                        room.getRoomState().equals(RoomState.AVAILABLE)
                        ? RoomState.BUSY
                        : RoomState.AVAILABLE);
        createRoomFacade.saveRoom(room);
    }

    public GuestRegistration setGuestRegistration(Long id) {
        GuestRegistration guestRegistration = getGuestRegistrationByIdFacade.getById(id);
            if (guestRegistration == null || !guestRegistration.getRegistrationStatus().booleanValue()) {
                throw new IntegrityValidation("is not allowed, registration is complete.");
            }
            guestRegistration.setRegistrationStatus(false);
            guestRegistration.setGuestCheckOut(new Date());
            guestRegistration.setServicePrice(guestRegistrationCalculatorHelper.guestRegistrationCalculator(guestRegistration.getGuestCheckIn(),
                    guestRegistration.getGuestCheckOut(), guestRegistration.getRoom().getRoomCategory().getBasePrice()));
            return guestRegistration;
        }

}
