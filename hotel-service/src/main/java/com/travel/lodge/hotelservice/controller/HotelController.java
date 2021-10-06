package com.travel.lodge.hotelservice.controller;

import com.travel.lodge.hotelservice.dto.CommonResponse;
import com.travel.lodge.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController {

    final HotelService hotelService;

    @GetMapping
    public CommonResponse getHotels(Principal principal) {
        return hotelService.getHotels();
    }

    @GetMapping("/check-availability")
    public CommonResponse checkHotelAvailability(@RequestParam Long hotelId,
                                         @RequestParam @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}$")
                                                 String checkInDateTime,
                                         @RequestParam @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}$")
                                                 String checkOutDateTime) throws ParseException {

        return hotelService.checkHotelAvailability(hotelId, checkInDateTime, checkOutDateTime);


    }
}
