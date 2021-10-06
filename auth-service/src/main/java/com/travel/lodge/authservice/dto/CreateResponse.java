package com.travel.lodge.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateResponse {
    private String status;
    private String message;
}
