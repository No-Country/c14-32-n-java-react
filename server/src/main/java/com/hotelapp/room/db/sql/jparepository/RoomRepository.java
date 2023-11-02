package com.hotelapp.room.db.sql.jparepository;

import com.hotelapp.room.db.sql.modeldata.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomData, Long> {
}
