package com.travel.lodge.hotelservice.repository;

import com.travel.lodge.hotelservice.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAll();

    @Query(value = "select r.roomId.id from Reservation r where (r.checkOutDateTime >= :inDate) and (r.checkInDateTime <= :outDate)")
    List<Long> findFilledRoomsForDate(@Param("inDate") Date checkInDate, @Param("outDate") Date checkOutDate);

    Optional<Reservation> findByReferenceNo(String referenceNo);
}

