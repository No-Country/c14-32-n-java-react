package com.hotelapp.categoryRoom.dto.mappers;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.dto.request.UpdateCategoryRequest;
import org.springframework.stereotype.Component;


@Component
public class CreateCategoryRequestMapper {

        public Category createCategoryRequestToCategory(UpdateCategoryRequest updateCategoryRequest){
            return new Category.CategoryBuilder()
                    .idCategory(updateCategoryRequest.idCategory())
                    .categoryName(updateCategoryRequest.categoryName())
                    .categoryDescription(updateCategoryRequest.categoryDescription())
                    .basePrice(updateCategoryRequest.basePrice())
                    .build();
        }
}
