package com.syncrosa.usersservice.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Account",
        description = "Schema to handle create account"
)
public class CreateAccountRequest {

    @Schema(
            description = "type of bank account",
            example = "Savings"
    )
    @NotBlank
    private String type;

    @Schema(
            description = "bank branch address",
            example = "1 Kaffu, Shibuya District"
    )
    @NotBlank
    private String branchAddress;

}
