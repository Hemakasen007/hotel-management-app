package com.travel.lodge.hotelservice.controller;

import com.travel.lodge.hotelservice.dto.CommonResponse;
import com.travel.lodge.hotelservice.dto.PaidRequest;
import com.travel.lodge.hotelservice.dto.PriceInformation;
import com.travel.lodge.hotelservice.service.InvoicePaymentService;
import com.travel.lodge.hotelservice.util.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment-invoice")
@RequiredArgsConstructor
public class InvoicePaymentController {

    final InvoicePaymentService invoicePaymentService;

    @GetMapping("price")
    public PriceInformation getTotalPayable(@RequestParam String refNo ){
        return invoicePaymentService.getPrice(refNo);
    }

    @PostMapping("update-paid")
    public CommonResponse updatePaid(@RequestBody PaidRequest paidRequest){
        invoicePaymentService.updatePaymentInvoice(paidRequest);
        return new CommonResponse(Consts.ResponseMessages.SUCCESS, null);
    }

}
