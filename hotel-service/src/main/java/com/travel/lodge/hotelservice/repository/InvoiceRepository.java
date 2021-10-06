package com.travel.lodge.hotelservice.repository;

import com.travel.lodge.hotelservice.domain.Hotel;
import com.travel.lodge.hotelservice.domain.Invoice;
import com.travel.lodge.hotelservice.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.ref.Reference;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    @Query("select i from Invoice i where i.reservation.referenceNo = :refNo and i.status = 'INVOICED'")
    Optional<Invoice> findReferenceNo(@Param("refNo") String refNo);
}
