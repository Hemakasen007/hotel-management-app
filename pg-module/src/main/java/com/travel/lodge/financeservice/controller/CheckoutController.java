package com.travel.lodge.financeservice.controller;

import com.travel.lodge.financeservice.dto.ChargeRequest;
import com.travel.lodge.financeservice.dto.PriceInformation;
import com.travel.lodge.financeservice.feign.HotelServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    final HotelServiceClient hotelServiceClient;
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model, String refNo) {
        try {
            PriceInformation priceInformation = hotelServiceClient.getTotalPayable(refNo);

            var price = priceInformation.getPrice();
            var intPrice = (int) Math.round(price);
            model.addAttribute("amount", intPrice * 100); // in cents
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("currency", ChargeRequest.Currency.USD);
            model.addAttribute("refNo", refNo);
            return "checkout";
        }catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "result";
        }
    }
}
