package com.hotelapp.categoryRoom.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateCategoryRequest(
        @NotNull
        Long idCategory,
        @NotNull
        @NotBlank
        String categoryName,
        @NotNull
        @NotBlank
        String categoryDescription,
        @NotNull
        BigDecimal basePrice
) {}
