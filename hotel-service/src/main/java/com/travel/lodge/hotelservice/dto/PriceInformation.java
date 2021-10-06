package com.travel.lodge.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Access;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class PriceInformation implements Serializable {
    private double price;
}
