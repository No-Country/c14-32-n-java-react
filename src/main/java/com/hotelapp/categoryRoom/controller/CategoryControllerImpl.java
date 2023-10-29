package com.hotelapp.categoryRoom.controller;

import com.hotelapp.categoryRoom.controller.validate.ValidateCategory;
import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.categoryRoom.dto.request.UpdateCategoryRequest;
import com.hotelapp.categoryRoom.services.*;
import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.hotelapp.commons.constants.GlobalApiConstant.*;

import static com.hotelapp.categoryRoom.constants.CategoryConstants.REQUEST_CATEGORY;
import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;
import static com.hotelapp.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;
import static com.hotelapp.customer.constants.CustomerConstants.REQUEST_CUSTOMER;


@RestController
@RequestMapping(REQUEST_CATEGORY )
public class CategoryControllerImpl extends GenericRestController implements CategoryController {

    private final CreateCategoryService createCategoryService;
    private final GetAllCategoryService getAllCategoryService;
    private final GetCategoryByIdService getCategoryByIdService;
    private final UpdateCategoryService updateCategoryService;
    private final DeleteCategoryByIdService deleteCategoryByIdService;

    public CategoryControllerImpl(CreateCategoryService createCategoryService, GetAllCategoryService getAllCategoryService, GetCategoryByIdService getCategoryByIdService,
            UpdateCategoryService updateCategoryService,DeleteCategoryByIdService deleteCategoryByIdService) {
        this.createCategoryService = createCategoryService;
        this.getAllCategoryService = getAllCategoryService;
        this.getCategoryByIdService = getCategoryByIdService;
        this.updateCategoryService = updateCategoryService;
        this.deleteCategoryByIdService = deleteCategoryByIdService;
    }

    @Override
    public ResponseEntity<CustomResponse> save(Category category,BindingResult bindingResult) {
        ValidateCategory.validateCategoryRows(category, bindingResult);
        if (bindingResult.hasErrors()){
            return bad(category,bindingResult.getFieldError().getDefaultMessage(),REQUEST_CATEGORY);
        }
        return create(createCategoryService.saveCategory(category),CREATED, REQUEST_CATEGORY);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllCategories(int numberPage) {
        return ok(getAllCategoryService.getAllCategoryPaginator(numberPage),null,REQUEST_CATEGORY);
    }

    @Override
    public ResponseEntity<CustomResponse> getCategoryById(Long id) {
        Category category = getCategoryByIdService.getCategoryById(id);
        if (category != null) return ok(category, null, REQUEST_CATEGORY);
        return notFound(null,NOT_FOUND, REQUEST_CATEGORY);
    }

    @Override
    public ResponseEntity<CustomResponse> update(UpdateCategoryRequest updateCategoryRequest) {
        Category updateCategoryData = updateCategoryService.updateCategory(updateCategoryRequest);
        if (updateCategoryData != null) {
            return ok(updateCategoryData,null,REQUEST_CATEGORY);
        }
        return notFound(null,NOT_FOUND, REQUEST_CATEGORY );
    }

    @Override
    public ResponseEntity<CustomResponse> deleteCategoryById(Long id) {
        deleteCategoryByIdService.deleteCategoryById(id);
        return ok(null,DELETED_SUCCESSFULLY, REQUEST_CATEGORY);
    }
}
