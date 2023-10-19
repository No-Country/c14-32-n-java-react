package com.hotelapp.booking.controller.validate;

import com.hotelapp.commons.controller.validators.Validator;
import com.hotelapp.commons.controller.validators.ValidatorObject;
import com.hotelapp.room.dto.model.Room;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.ZoneId;

import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_NULL;
import static java.util.Objects.isNull;


public class ValidateRoom extends Validator {
    private static final String OBJ = "room";

    public static void validateRoomRows(Room room, BindingResult bindingResult){
        roomNumberNotNull(room, bindingResult);
        roomStateNotNull(room,bindingResult);

    }
    public static void roomNumberNotNull(Room room, BindingResult bindingResult){
        if(!isNull(room.getRoomNumber())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("roomNumber")
                    .object(OBJ).build(), bindingResult);
        }
    }
    public static void roomStateNotNull(Room room, BindingResult bindingResult){
        if(!isNull(room.getRoomState())){
            validate(new ValidatorObject.ValidatorObjectBuilder()
                    .message(NOT_NULL)
                    .field("roomState")
                    .object(OBJ).build(), bindingResult);
        }
    }
}
