package com.hotelapp.categoryRoom.db.sql.mapper;

import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {
    public Category categoryDataToCategory(CategoryData categoryData){
        return new Category.CategoryBuilder()
                .idCategory(categoryData.getIdCategory())
                .categoryName(categoryData.getCategoryName())
                .categoryDescription(categoryData.getCategoryDescription())
                .basePrice(categoryData.getBasePrice())
                .build();
    }

    public CategoryData categoryToCategoryData(Category category){
        return new CategoryData.CategoryDataBuilder()
                .idCategory(category.getIdCategory())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .basePrice(category.getBasePrice())
                .build();
    }

}

