package com.hotelapp.categoryRoom.controller.validate;

import com.hotelapp.categoryRoom.dto.model.Category;
import com.hotelapp.commons.controller.validators.Validator;
import com.hotelapp.commons.controller.validators.ValidatorObject;
import org.springframework.validation.BindingResult;

import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_NULL;
import static java.util.Objects.isNull;

public class ValidateCategory extends Validator {

    private static final String OBJ = "Category";

    public static void validateCategoryRows(Category category, BindingResult bindingResult){
        categoryNameNotNull(category, bindingResult);
        categoryDescriptionNotNull(category,bindingResult);
        basePriceNotNull(category,bindingResult);
    }
    public static void categoryNameNotNull(Category category, BindingResult bindingResult){
        if(isNull(category.getCategoryName())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("categoryName")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void categoryDescriptionNotNull(Category category, BindingResult bindingResult){
        if(isNull(category.getCategoryDescription())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("categoryDescription")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void basePriceNotNull(Category category, BindingResult bindingResult){
        if(isNull(category.getBasePrice())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("basePrice")
                    .object(OBJ).build(), bindingResult);
        }
    }

}
