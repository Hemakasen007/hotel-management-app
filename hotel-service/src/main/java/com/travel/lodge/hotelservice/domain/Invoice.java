package com.travel.lodge.hotelservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double roomCharge;

    private Double miscCharges;

    private Double totalCharges;

    private Long userId;

    private String status;

    private String balanceTxIdPg;

    private String chargeIdPg;


    @OneToOne
    @JoinColumn(name = "referenceNo", nullable = false, referencedColumnName = "referenceNo")
    private Reservation reservation;
}
