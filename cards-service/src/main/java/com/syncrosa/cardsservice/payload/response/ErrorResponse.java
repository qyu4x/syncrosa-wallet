package com.syncrosa.cardsservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
