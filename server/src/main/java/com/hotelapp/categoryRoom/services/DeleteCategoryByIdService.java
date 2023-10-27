package com.hotelapp.categoryRoom.services;

import com.hotelapp.categoryRoom.facade.DeleteCategoryByIdFacade;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryByIdService {
    private final DeleteCategoryByIdFacade deleteCategoryByIdFacade;


    public DeleteCategoryByIdService(DeleteCategoryByIdFacade deleteCategoryByIdFacade) {
        this.deleteCategoryByIdFacade = deleteCategoryByIdFacade;
    }
    public void deleteCategoryById(Long id){
        deleteCategoryByIdFacade.deleteCategoryById(id);
    }
}
