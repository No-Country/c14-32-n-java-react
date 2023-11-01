package com.hotelapp.guestRegistration.db.sql.modeldata;


import com.hotelapp.booking.dto.model.enums.PaymentType;
import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import com.hotelapp.room.db.sql.modeldata.RoomData;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "guest_registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private CustomerData customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomData room;
    private Date guestCheckIn;
    private Date guestCheckOut;
    private BigDecimal servicePrice;
    private PaymentType paymentType;
    private Boolean registrationStatus;


    public static final class GuestRegistrationDataBuilder {
        private Long idRegistration;
        private CustomerData customer;
        private RoomData room;
        private Date guestCheckIn;
        private Date guestCheckOut;
        private BigDecimal servicePrice;
        private PaymentType paymentType;
        private Boolean registrationStatus;

        public GuestRegistrationDataBuilder() {
        }

        public static GuestRegistrationData.GuestRegistrationDataBuilder aGuestRegistrationData() {
            return new GuestRegistrationData.GuestRegistrationDataBuilder();
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder idRegistration(Long idRegistration) {
            this.idRegistration = idRegistration;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder customer(CustomerData customer) {
            this.customer = customer;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder room(RoomData room) {
            this.room = room;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder guestCheckIn(Date guestCheckIn ) {
            this.guestCheckIn = guestCheckIn;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder guestCheckOut(Date guestCheckOut ) {
            this.guestCheckOut = guestCheckOut;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder servicePrice(BigDecimal servicePrice ) {
            this.servicePrice = servicePrice;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public GuestRegistrationData.GuestRegistrationDataBuilder registrationStatus(Boolean registrationStatus) {
            this.registrationStatus = registrationStatus;
            return this;
        }



        public GuestRegistrationData build() {
            GuestRegistrationData guestRegistration = new GuestRegistrationData();
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
