package com.hotelapp.categoryRoom.db.sql.jparepository;

import com.hotelapp.categoryRoom.db.sql.modeldata.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <CategoryData, Long>{
}
