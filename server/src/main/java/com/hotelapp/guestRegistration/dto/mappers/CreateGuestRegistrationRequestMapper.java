package com.hotelapp.guestRegistration.dto.mappers;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class CreateGuestRegistrationRequestMapper {

    public GuestRegistration createBookingToGuestRegistration(Booking booking){
        return new GuestRegistration.GuestRegistrationBuilder()
                .customer(booking.getCustomer())
                .room(booking.getRoom())
                .guestCheckIn(new Date())
                .servicePrice(new BigDecimal(0))
                .paymentType(booking.getPaymentType())
                .registrationStatus(true)
                .build();
    }
}
