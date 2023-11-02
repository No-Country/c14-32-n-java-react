package com.hotelapp.guestRegistration.db.sql.jparepository;

import com.hotelapp.guestRegistration.db.sql.modeldata.GuestRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRegistrationRepository extends JpaRepository<GuestRegistrationData, Long> {
}
