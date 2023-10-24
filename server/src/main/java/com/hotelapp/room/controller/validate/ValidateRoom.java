package com.hotelapp.room.controller.validate;

import com.hotelapp.commons.controller.validators.Validator;
import com.hotelapp.commons.controller.validators.ValidatorObject;
import com.hotelapp.room.dto.request.CreateRoomRequest;
import org.springframework.validation.BindingResult;


import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_NULL;
import static java.util.Objects.isNull;


public class ValidateRoom extends Validator {
    private static final String OBJ = "room";

    public static void validateRoomRows(CreateRoomRequest room, BindingResult bindingResult){
        roomNumberNotNull(room, bindingResult);
        roomStateNotNull(room,bindingResult);
        roomCategoryNotNull(room,bindingResult);
    }

    public static void roomNumberNotNull(CreateRoomRequest room, BindingResult bindingResult){
        if(isNull(room.getRoomNumber())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("roomNumber")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void roomStateNotNull(CreateRoomRequest room, BindingResult bindingResult){
        if(isNull(room.getRoomState())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("roomState")
                    .object(OBJ).build(), bindingResult);
        }
    }

    public static void roomCategoryNotNull(CreateRoomRequest room, BindingResult bindingResult){
        if(isNull(room.getRoomState())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("roomCategory")
                    .object(OBJ).build(), bindingResult);
        }
    }
}
