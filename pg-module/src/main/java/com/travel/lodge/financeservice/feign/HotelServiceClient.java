package com.travel.lodge.financeservice.feign;

import com.travel.lodge.financeservice.dto.PaidRequest;
import com.travel.lodge.financeservice.dto.PriceInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hotelClient", url = "${service.hotel.base}")
public interface HotelServiceClient {

    @GetMapping("${service.hotel.get-price}")
    PriceInformation getTotalPayable(@RequestParam String refNo);

    @PostMapping("${service.hotel.update-invoice}")
    Object updatePaid(@RequestBody PaidRequest paidRequest);
}
