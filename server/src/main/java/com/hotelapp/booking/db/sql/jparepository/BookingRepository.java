package com.hotelapp.booking.db.sql.jparepository;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingData, Long> {
    List<BookingData> findByCustomer_FirstnameContainingAndCustomer_LastnameContainingIgnoreCase(String firstname, String lastname);


}
