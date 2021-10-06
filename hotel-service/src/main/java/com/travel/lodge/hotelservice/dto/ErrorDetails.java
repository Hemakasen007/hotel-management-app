package com.travel.lodge.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
    private String status;
    private String message;
    private String details;
    private Object data;
    private Map<String, Object> fieldErrors = new HashMap<>();

    public ErrorDetails(String status, String message, String details, Object data) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.data = data;
    }

    public ErrorDetails(String message, String status,Map<String, Object> fieldErrors ) {
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
}
