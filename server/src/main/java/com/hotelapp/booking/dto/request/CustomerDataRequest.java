package com.hotelapp.booking.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerDataRequest(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname
) {
}
