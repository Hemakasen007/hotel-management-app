package com.travel.lodge.financeservice.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.travel.lodge.financeservice.dto.ChargeRequest;
import com.travel.lodge.financeservice.dto.PaidRequest;
import com.travel.lodge.financeservice.feign.HotelServiceClient;
import com.travel.lodge.financeservice.service.StripeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ChargeController {

    final StripeService paymentService;
    final HotelServiceClient hotelServiceClient;

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException, AuthenticationException {
        try {
            chargeRequest.setDescription("Example charge");
            chargeRequest.setCurrency(ChargeRequest.Currency.USD);
            Charge charge = paymentService.charge(chargeRequest);
            hotelServiceClient.updatePaid(new PaidRequest(chargeRequest.getRefNo(), charge.getBalanceTransaction(), charge.getId()));
            model.addAttribute("id", charge.getId());
            model.addAttribute("status", charge.getStatus());
            model.addAttribute("chargeId", charge.getId());
            model.addAttribute("balance_transaction", charge.getBalanceTransaction());
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "result";
        }
    }


    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

}
