package com.hotelapp.booking.services.validation;

import com.hotelapp.booking.dto.model.Booking;
import com.hotelapp.infra.error.IntegrityValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class ValidDates implements BookingsValidator{

    /**
     * validates that the date is appropriate
     * @param booking
     */
    @Override
    public void validation(Booking booking) {
        LocalDate localCheckInDate = booking.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCheckOutDate = booking.getCheckOutDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println();
        if (localCheckOutDate.isBefore(localCheckInDate) || booking.getCheckInDate().equals(booking.getCheckOutDate())) {
            throw new IntegrityValidation("the date check out is less than or equal to the date check in");
        }
    }
}
