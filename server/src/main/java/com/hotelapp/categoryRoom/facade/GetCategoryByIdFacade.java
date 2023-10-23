package com.hotelapp.categoryRoom.facade;

import com.hotelapp.categoryRoom.dto.model.Category;


public interface GetCategoryByIdFacade {
    Category getCategoryById(Long id);
}
