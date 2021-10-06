package com.travel.lodge.hotelservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ReservationRequest {
    @NotNull
    private Long hotelId;
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}$")
    private String checkInDate;
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}$")
    private String checkOutDate;
}
