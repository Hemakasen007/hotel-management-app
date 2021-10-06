package com.travel.lodge.hotelservice.repository;

import com.travel.lodge.hotelservice.domain.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

    List<Hotel> findAll();
}
