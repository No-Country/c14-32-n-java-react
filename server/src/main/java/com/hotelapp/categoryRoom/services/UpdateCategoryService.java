package com.hotelapp.categoryRoom.services;

import com.hotelapp.categoryRoom.dto.mappers.CreateCategoryRequestMapper;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.dto.request.UpdateCategoryRequest;
import com.hotelapp.categoryRoom.facade.UpdateCategoryFacade;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategoryService {

    private final UpdateCategoryFacade updateCategoryFacade;
    private final CreateCategoryRequestMapper createCategoryRequestMapper;

    public UpdateCategoryService(UpdateCategoryFacade updateCategoryFacade,
                                 CreateCategoryRequestMapper createCategoryRequestMapper){
        this.updateCategoryFacade = updateCategoryFacade;
        this.createCategoryRequestMapper = createCategoryRequestMapper;
    }


    public Category updateCategory(UpdateCategoryRequest updateCategoryRequest){
        Category category = createCategoryRequestMapper.createCategoryRequestToCategory(updateCategoryRequest);
        return updateCategoryFacade.update(category);
    }
}
