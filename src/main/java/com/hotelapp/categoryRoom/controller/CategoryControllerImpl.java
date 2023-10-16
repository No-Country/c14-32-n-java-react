package com.hotelapp.categoryRoom.controller;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;

import com.hotelapp.categoryRoom.services.CreateCategoryService;
import com.hotelapp.categoryRoom.services.DeleteCategoryByIdService;
import com.hotelapp.categoryRoom.services.GetCategoryByIdService;
import com.hotelapp.categoryRoom.services.GetAllCategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.categoryRoom.constants.CategoryConstants.REQUEST_CATEGORY;
import static com.hotelapp.commons.constants.GlobalApiConstant.CREATED;
import static com.hotelapp.commons.constants.GlobalApiConstant.DELETED_SUCCESSFULLY;


@RestController
@RequestMapping(REQUEST_CATEGORY )
public class CategoryControllerImpl extends GenericRestController implements CategoryController {

    private final CreateCategoryService createCategoryService;
    private final GetAllCategoryService getAllCategoryService;
    private final GetCategoryByIdService getCategoryByIdService;
    private final DeleteCategoryByIdService deleteCategoryByIdService;

    public CategoryControllerImpl(CreateCategoryService createCategoryService, GetAllCategoryService getAllCategoryService, GetCategoryByIdService getCategoryByIdService, DeleteCategoryByIdService deleteCategoryByIdService) {
        this.createCategoryService = createCategoryService;
        this.getAllCategoryService = getAllCategoryService;
        this.getCategoryByIdService = getCategoryByIdService;
        this.deleteCategoryByIdService = deleteCategoryByIdService;
    }

    @Override
    public ResponseEntity<CustomResponse> save(Category category) {
        return create(createCategoryService.saveCategory(category),CREATED, REQUEST_CATEGORY);
    }

    @Override
    public ResponseEntity<CustomResponse> getAllCategorys(int numberPage) {
        return ok(getAllCategoryService.getAllCategorysPaginator(numberPage),null,REQUEST_CATEGORY);
    }

    @Override
    public ResponseEntity<CustomResponse> getCategoryById(Long id) {
        return ok(getCategoryByIdService.getCategoryById(id), null, REQUEST_CATEGORY);
    }

    @Override
    public ResponseEntity<CustomResponse> deleteCategoryById(Long id) {
        deleteCategoryByIdService.deleteCategoryById(id);
        return ok(null,DELETED_SUCCESSFULLY, REQUEST_CATEGORY);
    }
}
