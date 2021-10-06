package com.travel.lodge.hotelservice.feign;

import com.travel.lodge.hotelservice.dto.HotelUserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userClient", url = "${services.user.base}")
public interface UserServiceClient {

    @GetMapping("${services.user.get-by-email}")
    HotelUserDetails getByUsername(@RequestHeader("Authorization")String token, @RequestParam String username);


}
