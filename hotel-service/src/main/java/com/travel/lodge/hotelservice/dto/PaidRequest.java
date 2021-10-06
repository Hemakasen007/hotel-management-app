package com.travel.lodge.hotelservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaidRequest implements Serializable {
    private String refNo;
    private String chargeId;
    private String balanceTransaction;
}
