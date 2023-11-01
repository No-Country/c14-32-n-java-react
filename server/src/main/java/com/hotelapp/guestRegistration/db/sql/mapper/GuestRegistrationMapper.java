package com.hotelapp.guestRegistration.db.sql.mapper;

import com.hotelapp.customer.db.sql.mapper.CustomerMapper;
import com.hotelapp.guestRegistration.db.sql.modeldata.GuestRegistrationData;
import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.room.db.sql.mapper.RoomMapper;
import org.springframework.stereotype.Component;

@Component
public class GuestRegistrationMapper {

    private final CustomerMapper customerMapper;
    private final RoomMapper roomMapper;

    public GuestRegistrationMapper(CustomerMapper customerMapper, RoomMapper roomMapper){
        this.customerMapper = customerMapper;
        this.roomMapper = roomMapper;
    }


    public GuestRegistrationData guestRegistrationToGuestRegistrationData(GuestRegistration  guestRegistration){

        return new GuestRegistrationData.GuestRegistrationDataBuilder()
                .idRegistration(guestRegistration.getIdRegistration())
                .customer(customerMapper.customerToCustomerData(guestRegistration.getCustomer()))
                .room(roomMapper.roomToRoomData(guestRegistration.getRoom()))
                .guestCheckIn(guestRegistration.getGuestCheckIn())
                .guestCheckOut(guestRegistration.getGuestCheckOut())
                .servicePrice(guestRegistration.getServicePrice())
                .paymentType(guestRegistration.getPaymentType())
                .registrationStatus(guestRegistration.getRegistrationStatus())
                .build();
    }

    public GuestRegistration guestRegistrationDataToGuestRegistration(GuestRegistrationData guestRegistrationData){

        return new GuestRegistration.GuestRegistrationBuilder()
                .idRegistration(guestRegistrationData.getIdRegistration())
                .customer(customerMapper.customerDataToCustomer(guestRegistrationData.getCustomer()))
                .room(roomMapper.roomDataToRoom(guestRegistrationData.getRoom()))
                .guestCheckIn(guestRegistrationData.getGuestCheckIn())
                .guestCheckOut(guestRegistrationData.getGuestCheckOut())
                .servicePrice(guestRegistrationData.getServicePrice())
                .paymentType(guestRegistrationData.getPaymentType())
                .registrationStatus(guestRegistrationData.getRegistrationStatus())
                .build();
    }
}
