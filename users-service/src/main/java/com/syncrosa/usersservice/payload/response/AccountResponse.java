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
        name = "Account Response",
        description = "Schema to handle success create account"
)
public class AccountResponse {

    @Schema(
            description = "number of account",
            example = "182351252122"
    )
    private Long number;

    @Schema(
            description = "type of account",
            example = "Savings"
    )
    private String type;

    @Schema(
            description = "bank branch address",
            example = "1 Kaffu, Shibuya District"
    )
    private String branchAddress;

    @Schema(
            description = "created at of account",
            example = "1210291201"
    )
    private Long createdAt;

    @Schema(
            description = "updated at of account",
            example = "1210291201"
    )
    private Long updatedAt;
}
