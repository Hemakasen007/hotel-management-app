package com.travel.lodge.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.lodge.hotelservice.util.Consts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Consts.ResponseMessages status;
    private String message;
    private Object data;

    public CommonResponse(Consts.ResponseMessages status, String message){
        this.status = status;
        this.message = message;
    }
}
