package com.hotelapp.guestRegistration.dto.model;

import com.hotelapp.booking.dto.model.enums.PaymentType;
import com.hotelapp.customer.dto.model.Customer;
import com.hotelapp.room.dto.model.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestRegistration {

    private Long idRegistration;

    private Date guestCheckIn;
    private Date guestCheckOut;
    private BigDecimal servicePrice;
    private PaymentType paymentType;
    private Boolean registrationStatus;
    private Customer customer;
    private Room room;


    public static final class GuestRegistrationBuilder {
        private Long idRegistration;
        private Date guestCheckIn;
        private Date guestCheckOut;
        private BigDecimal servicePrice;
        private PaymentType paymentType;
        private Boolean registrationStatus;
        private Customer customer;
        private Room room;

        public GuestRegistrationBuilder() {
        }

        public static GuestRegistration.GuestRegistrationBuilder aGuestRegistration() {
            return new GuestRegistration.GuestRegistrationBuilder();
        }

        public GuestRegistration.GuestRegistrationBuilder idRegistration(Long idRegistration) {
            this.idRegistration = idRegistration;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder room(Room room) {
            this.room = room;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder guestCheckIn(Date guestCheckIn ) {
            this.guestCheckIn = guestCheckIn;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder guestCheckOut(Date guestCheckOut ) {
            this.guestCheckOut = guestCheckOut;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder servicePrice(BigDecimal servicePrice ) {
            this.servicePrice = servicePrice;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public GuestRegistration.GuestRegistrationBuilder registrationStatus(Boolean registrationStatus) {
            this.registrationStatus = registrationStatus;
            return this;
        }





        public GuestRegistration build() {
            GuestRegistration guestRegistration = new GuestRegistration();
            guestRegistration.setIdRegistration(idRegistration);
            guestRegistration.setCustomer(customer);
            guestRegistration.setRoom(room);
            guestRegistration.setGuestCheckIn(guestCheckIn);
            guestRegistration.setGuestCheckOut(guestCheckOut);
            guestRegistration.setServicePrice(servicePrice);
            guestRegistration.setPaymentType(paymentType);
            guestRegistration.setRegistrationStatus(registrationStatus);
            return guestRegistration;
        }
    }
}
