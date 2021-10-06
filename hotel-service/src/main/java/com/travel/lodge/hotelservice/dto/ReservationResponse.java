package com.travel.lodge.hotelservice.dto;

import com.travel.lodge.hotelservice.util.Consts;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationResponse {
    private Consts.ResponseMessages status;
    private String emailAddress;
    private String referenceNo;
    private Double totalPayable;
    private String message;
    private String link;
}
