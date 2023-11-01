package com.hotelapp.room.dto.request;

public record GuestCheckRequest(
        Long idCustomer,
        Long idBooking
) {

}
