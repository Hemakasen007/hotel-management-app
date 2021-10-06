package com.travel.lodge.financeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaidRequest implements Serializable {
    private String refNo;
    private String chargeId;
    private String balanceTransaction;
}
