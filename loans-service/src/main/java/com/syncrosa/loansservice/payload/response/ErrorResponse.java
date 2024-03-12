package com.syncrosa.loansservice.payload.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse <T>{

    private String apiPath;

    private Integer errorCode;

    private T errorMessage;

    private LocalDateTime errorTime;
}
