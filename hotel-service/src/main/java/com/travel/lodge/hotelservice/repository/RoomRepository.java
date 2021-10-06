package com.travel.lodge.hotelservice.repository;

import com.travel.lodge.hotelservice.domain.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findAll();


}
