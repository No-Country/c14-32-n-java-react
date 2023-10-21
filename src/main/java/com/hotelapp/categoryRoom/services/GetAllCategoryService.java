package com.hotelapp.categoryRoom.services;


import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.facade.GetAllCategoryFacade;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class GetAllCategoryService {
    private final GetAllCategoryFacade getAllCategoryFacade;

    public GetAllCategoryService(GetAllCategoryFacade getAllCategoryFacade) {
        this.getAllCategoryFacade = getAllCategoryFacade;
    }


 public Page<Category> getAllCategoryPaginator(int numberPage){
        return getAllCategoryFacade.getAllCategoryPaginator(numberPage);
    }

}
