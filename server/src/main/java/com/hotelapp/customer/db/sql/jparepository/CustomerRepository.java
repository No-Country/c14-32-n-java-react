package com.hotelapp.customer.db.sql.jparepository;

import com.hotelapp.customer.db.sql.modeldata.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerData, Long> {
}
