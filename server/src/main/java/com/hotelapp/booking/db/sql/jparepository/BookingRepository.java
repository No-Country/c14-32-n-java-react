package com.hotelapp.booking.db.sql.jparepository;

import com.hotelapp.booking.db.sql.modeldata.BookingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingData, Long> {
}
