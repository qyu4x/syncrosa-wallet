package com.syncrosa.usersservice.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "WebResponse",
        description = "Schema to handle success operation"
)
public class WebResponse <T> {

    @Schema(
            description = "response code",
            example = "200"
    )
    private Integer statusCode;

    @Schema(
            description = "response message",
            example = "OK"
    )
    private String message;

    @Schema(
            description = "response data"
    )
    private T data;
}
