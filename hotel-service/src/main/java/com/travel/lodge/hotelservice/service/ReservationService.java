package com.travel.lodge.hotelservice.service;

import com.travel.lodge.hotelservice.domain.Room;
import com.travel.lodge.hotelservice.dto.CommonResponse;
import com.travel.lodge.hotelservice.dto.ReservationRequest;
import com.travel.lodge.hotelservice.dto.ReservationResponse;
import com.travel.lodge.hotelservice.feign.UserServiceClient;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import static com.travel.lodge.hotelservice.util.Consts.AppMessages.RESERVATION_MARKED;
import static com.travel.lodge.hotelservice.util.Consts.ResponseMessages.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    final UserServiceClient userServiceClient;
    final ReservationTransactionDelegate reservationTransactionDelegate;

    @Value("${checkout.link}")
    private String url;

    public ReservationResponse makeReservation(String token, Principal principal, CommonResponse request, ReservationRequest reservationRequest) throws ParseException {
        var rooms = (List<Room>) request.getData();
        var username = ((KeycloakAuthenticationToken) principal).getAccount()
                .getKeycloakSecurityContext().getToken().getPreferredUsername();
        try {
            var user = userServiceClient.getByUsername(token,username);
            var reservation = reservationTransactionDelegate.saveReservation(user, rooms.get(0), reservationRequest);
            //miscellaneous charges are not in this scope hence 0 is added
            var invoice = reservationTransactionDelegate.generateAndSaveInvoice(reservation, 0.0);
            return new ReservationResponse(SUCCESS, username, reservation.getReferenceNo(), invoice.getTotalCharges(),
                    RESERVATION_MARKED, url+reservation.getReferenceNo());
        } catch (RetryableException | ParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        }


    }


}
