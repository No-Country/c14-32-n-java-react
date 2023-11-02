package com.hotelapp.categoryRoom.facade;


import com.hotelapp.categoryRoom.dto.model.Category;
import org.springframework.data.domain.Page;

public interface GetAllCategoryFacade {
    Page<Category> getAllCategoryPaginator(int numberPage);
}
