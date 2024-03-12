package com.syncrosa.loansservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse <T>{

    private Integer statusCode;

    private String message;

    private T data;

}
