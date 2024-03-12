package com.syncrosa.usersservice.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "ErrorResponse",
        description = "Schema to handle error operation"
)
public class ErrorResponse <T>{

    @Schema(
            description = "error api path",
            example = "/api/v1/accounts"
    )
    private String apiPath;

    @Schema(
            description = "error code",
            example = "404"
    )
    private Integer errorCode;

    @Schema(
            description = "error message",
            example = "Customer not found"
    )
    private T errorMessage;

    @Schema(
            description = "error time",
            example = "2024-10-10T12:12:30.34934Z"
    )
    private LocalDateTime errorTime;

}
