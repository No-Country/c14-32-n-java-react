package com.hotelapp.guestRegistration.controller;

import com.hotelapp.commons.controller.GenericRestController;
import com.hotelapp.commons.dto.response.CustomResponse;
import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.guestRegistration.dto.request.RegistrationCheckOutRequest;
import com.hotelapp.guestRegistration.dto.request.RegistrationRequest;
import com.hotelapp.guestRegistration.services.CreateRegistrationService;
import com.hotelapp.guestRegistration.services.EndOfStayService;
import com.hotelapp.guestRegistration.services.GetAllRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hotelapp.commons.constants.GlobalApiConstant.NOT_FOUND;
import static com.hotelapp.guestRegistration.constants.GuestConstants.REQUEST_GUEST_REGISTRATION;

@RestController
@RequestMapping(REQUEST_GUEST_REGISTRATION)
public class GuestRegistrationControllerImpl extends GenericRestController implements GuestRegistrationController{

    private final CreateRegistrationService createRegistrationService;
    private final GetAllRegistrationService getAllRegistrationService;

    private final EndOfStayService endOfStayService;

    public GuestRegistrationControllerImpl(CreateRegistrationService createRegistrationService,
                                           GetAllRegistrationService getAllRegistrationService,
                                           EndOfStayService endOfStayService
    ){
        this.createRegistrationService = createRegistrationService;
        this.getAllRegistrationService = getAllRegistrationService;
        this.endOfStayService = endOfStayService;
    }


    @Override
    public ResponseEntity<CustomResponse> saveCheckInRegistration(RegistrationRequest registrationRequest) {
        return ok(createRegistrationService.saveRegistration(registrationRequest.idBooking()),
                null,REQUEST_GUEST_REGISTRATION);
    }

    @Override
    public ResponseEntity<CustomResponse> saveCheckOutRegistration(RegistrationCheckOutRequest registrationCheckOutRequest) {
        GuestRegistration guestRegistration = endOfStayService.endOfStay(registrationCheckOutRequest.idRegistration());
        if (guestRegistration != null){
            return ok(guestRegistration, null,REQUEST_GUEST_REGISTRATION);
        }
        return notFound(null,NOT_FOUND, REQUEST_GUEST_REGISTRATION);

    }

    @Override
    public ResponseEntity<CustomResponse> getAllRegistration(int numberPage){
        return ok(getAllRegistrationService.getAll(numberPage),null,REQUEST_GUEST_REGISTRATION);
    }

    @Override
    public ResponseEntity<CustomResponse> getRegistrationById(Long id) {
        return null;
    }
}
