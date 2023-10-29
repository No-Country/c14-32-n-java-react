package com.hotelapp.categoryRoom.db.sql.jpaimpl;

import com.hotelapp.categoryRoom.db.sql.jparepository.CategoryRepository;
import com.hotelapp.categoryRoom.db.sql.mapper.CategoryMapper;
import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.facade.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryImpl implements CreateCategoryFacade, GetAllCategoryFacade, GetCategoryByIdFacade,
        UpdateCategoryFacade,DeleteCategoryByIdFacade {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryMapper.categoryDataToCategory(categoryRepository
                .save(categoryMapper.categoryToCategoryData(category)));
    }


    @Override
    public Page<Category> getAllCategoryPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        Page<CategoryData> categoryData =categoryRepository.findAll(page);
        return categoryData.map(categoryMapper::categoryDataToCategory);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<CategoryData> categoryFinded = categoryRepository.findById(id);
        if(categoryFinded.isPresent()){
            return categoryMapper.categoryDataToCategory(categoryFinded.get());
        }
        return null;
    }

    @Override
    public Category update(Category category) {
        Optional<CategoryData> categoryDataOptional = categoryRepository
                .findById(category.getIdCategory());
        if (categoryDataOptional.isPresent()){
            CategoryData updateCategoryData = categoryRepository
                    .save(categoryMapper.categoryToCategoryData(category));
            return categoryMapper.categoryDataToCategory(updateCategoryData);
        }
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }


}
