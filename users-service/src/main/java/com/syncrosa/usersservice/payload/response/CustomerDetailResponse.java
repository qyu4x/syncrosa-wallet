package com.syncrosa.usersservice.payload.response;

import com.syncrosa.usersservice.payload.client.CardResponse;
import com.syncrosa.usersservice.payload.client.LoanResponse;
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
        description = "Schema to handle success fetch accounts, cards and loans"
)
public class CustomerDetailResponse {

    @Schema(
            description = "id of customer",
            example = "iXkjdkfjksdfIy"
    )
    private String id;

    @Schema(
            description = "name of customer",
            example = "Chino Kaffu"
    )
    private String name;

    @Schema(
            description = "email of customer",
            example = "chinokaffu@gmail.com"
    )
    private String email;

    @Schema(
            description = "phone number of customer",
            example = "082351252122"
    )
    private String phone;

    private AccountResponse account;

    private CardResponse card;

    private LoanResponse loan;

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
