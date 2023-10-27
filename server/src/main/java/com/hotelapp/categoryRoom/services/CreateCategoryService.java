package com.hotelapp.categoryRoom.services;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.facade.CreateCategoryFacade;
import org.springframework.stereotype.Service;
@Service
public class CreateCategoryService {
   private final CreateCategoryFacade createCategoryFacade;

    public CreateCategoryService(CreateCategoryFacade createCategoryFacade) {
        this.createCategoryFacade = createCategoryFacade;
    }

    public Category saveCategory(Category category) {
        return createCategoryFacade.saveCategory(category);
    }
}
