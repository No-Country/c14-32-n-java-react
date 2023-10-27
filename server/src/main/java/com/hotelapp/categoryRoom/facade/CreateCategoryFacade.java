package com.hotelapp.categoryRoom.facade;

import com.hotelapp.categoryRoom.dto.model.Category;


public interface CreateCategoryFacade {
    Category saveCategory(Category category);
}
