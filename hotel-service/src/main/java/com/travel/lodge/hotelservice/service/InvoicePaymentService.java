package com.travel.lodge.hotelservice.service;

import com.travel.lodge.hotelservice.dto.PaidRequest;
import com.travel.lodge.hotelservice.dto.PriceInformation;
import com.travel.lodge.hotelservice.exception.InvoiceNotFoundException;
import com.travel.lodge.hotelservice.repository.InvoiceRepository;
import com.travel.lodge.hotelservice.repository.ReservationRepository;
import com.travel.lodge.hotelservice.util.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvoicePaymentService {

    final InvoiceRepository invoiceRepository;
    final ReservationRepository reservationRepository;

    public PriceInformation getPrice(String refNo){
        var invoice = invoiceRepository.findReferenceNo(refNo).orElseThrow(InvoiceNotFoundException::new);
        return new PriceInformation(invoice.getTotalCharges());
    }


    @Transactional
    public Object updatePaymentInvoice(PaidRequest paidRequest){
        var invoice = invoiceRepository.findReferenceNo(paidRequest.getRefNo())
                .orElseThrow(InvoiceNotFoundException::new);
        invoice.setBalanceTxIdPg(paidRequest.getBalanceTransaction());
        invoice.setChargeIdPg(paidRequest.getChargeId());
        invoice.setStatus(Consts.InvoiceStatus.PAID.name());

        var reservation  = reservationRepository.
                findByReferenceNo(invoice.getReservation().getReferenceNo()).orElseThrow(InvoiceNotFoundException::new);;
        reservation.setStatus(Consts.ReservationStatus.RESERVED.name());
        reservationRepository.save(reservation);
        return invoiceRepository.save(invoice);
    }
}
