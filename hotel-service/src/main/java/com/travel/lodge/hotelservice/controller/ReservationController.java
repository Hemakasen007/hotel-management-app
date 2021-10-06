package com.travel.lodge.hotelservice.controller;

import com.travel.lodge.hotelservice.dto.CommonResponse;
import com.travel.lodge.hotelservice.dto.ReservationRequest;
import com.travel.lodge.hotelservice.dto.ReservationResponse;
import com.travel.lodge.hotelservice.service.HotelService;
import com.travel.lodge.hotelservice.service.ReservationService;
import com.travel.lodge.hotelservice.util.Consts;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    final ReservationService reservationService;
    final HotelService hotelService;

    @PostMapping
    public ReservationResponse makeReservation(@RequestHeader("Authorization") String token,
                                               Principal principal, @RequestBody ReservationRequest request) throws ParseException {
        //get the unbooked rooms

        var availabilityRes = hotelService.checkHotelAvailability(request.getHotelId(),
                request.getCheckInDate(), request.getCheckOutDate());
        return reservationService.makeReservation(token,principal, availabilityRes, request);
    }
}
