package com.hotelapp.categoryRoom.controller;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.dto.request.UpdateCategoryRequest;
import com.hotelapp.commons.dto.response.CustomResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.hotelapp.commons.constants.GlobalApiConstant.GENERIC_PAGINATOR_PARAM;
import static com.hotelapp.commons.constants.GlobalApiConstant.ID_PARAM;

public interface CategoryController {

    @PostMapping
    ResponseEntity<CustomResponse> save(@Valid @RequestBody Category category, BindingResult bindingResult);

    @GetMapping(GENERIC_PAGINATOR_PARAM)
    ResponseEntity<CustomResponse> getAllCategories(@PathVariable int numberPage);

    @GetMapping(ID_PARAM)
    ResponseEntity<CustomResponse> getCategoryById(@PathVariable Long id);

    @PutMapping
    ResponseEntity<CustomResponse> update(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest);

    @DeleteMapping(ID_PARAM)
    ResponseEntity<CustomResponse> deleteCategoryById(@PathVariable Long id);

}
