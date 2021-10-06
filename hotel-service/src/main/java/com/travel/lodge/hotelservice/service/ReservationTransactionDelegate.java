package com.travel.lodge.hotelservice.service;

import com.travel.lodge.hotelservice.domain.Invoice;
import com.travel.lodge.hotelservice.domain.Reservation;
import com.travel.lodge.hotelservice.domain.Room;
import com.travel.lodge.hotelservice.dto.HotelUserDetails;
import com.travel.lodge.hotelservice.dto.ReservationRequest;
import com.travel.lodge.hotelservice.repository.InvoiceRepository;
import com.travel.lodge.hotelservice.repository.ReservationRepository;
import com.travel.lodge.hotelservice.repository.RoomRepository;
import com.travel.lodge.hotelservice.util.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ReservationTransactionDelegate {

    final ReservationRepository reservationRepository;
    final RoomRepository roomRepository;
    final InvoiceRepository invoiceRepository;

    private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm";

    @Transactional
    public Reservation saveReservation(HotelUserDetails user, Room room, ReservationRequest request) throws ParseException {
        var reservation = new Reservation();
        var sdf =  new SimpleDateFormat(DATE_PATTERN);
        var checkIn =  parseDate(sdf, request.getCheckInDate().replace("T", " "));
        var checkOut =  parseDate(sdf, request.getCheckOutDate().replace("T", " "));
        room.setGuestId(user.getId());
        room.setBookedStatus(Consts.BookedStatus.BOOKED.name());
        roomRepository.save(room);

        reservation.setGuestId(user.getId());
        reservation.setCheckInDateTime(checkIn);
        reservation.setCheckOutDateTime(checkOut);
        reservation.setStatus(Consts.ReservationStatus.MARKED.name());
        reservation.setReferenceNo(Consts.ConstString.PREFIX+System.currentTimeMillis());
        reservation.setRoomId(room);
        return reservationRepository.save(reservation);

    }

    public Invoice generateAndSaveInvoice(Reservation reservation, double miscCharge){
        var price = reservation.getRoomId().getPrice();
        //getting room price to calculate total
        var checkInDate = reservation.getCheckInDateTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        var checkOutDate = reservation.getCheckOutDateTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        var noOfDays = checkInDate.until(checkOutDate, ChronoUnit.DAYS);
        var roomCharge = price*noOfDays;
        var invoice = new Invoice();

        invoice.setRoomCharge(roomCharge);
        invoice.setUserId(reservation.getGuestId());
        invoice.setMiscCharges(miscCharge);
        invoice.setTotalCharges(roomCharge+miscCharge);
        invoice.setStatus(Consts.InvoiceStatus.INVOICED.name());
        invoice.setReservation(reservation);

        return invoiceRepository.save(invoice);

    }

    private Date parseDate(SimpleDateFormat sdf, String date) throws ParseException {
        return sdf.parse(date);
    }
}
