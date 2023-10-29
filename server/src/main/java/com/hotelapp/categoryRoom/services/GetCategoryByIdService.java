package com.hotelapp.categoryRoom.services;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.facade.GetCategoryByIdFacade;

import org.springframework.stereotype.Service;

@Service
public class GetCategoryByIdService {
    private final GetCategoryByIdFacade getCategoryByIdFacade;


    public GetCategoryByIdService(GetCategoryByIdFacade getCategoryByIdFacade) {
        this.getCategoryByIdFacade = getCategoryByIdFacade;
    }

    public Category getCategoryById(Long id){
        return getCategoryByIdFacade.getCategoryById(id);
    }
}
