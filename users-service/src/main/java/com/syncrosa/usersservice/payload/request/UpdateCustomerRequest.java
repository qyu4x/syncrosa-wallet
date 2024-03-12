package com.syncrosa.usersservice.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Customer",
        description = "Schema to handle create customer"
)
public class UpdateCustomerRequest {

    @Schema(
            description = "name of customer account",
            example = "Kaffu Chino"
    )
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @Schema(
            description = "email of customer account",
            example = "kaffuchan@gmail.com"
    )
    @NotBlank
    @Email
    private String email;

    @Schema(
            description = "phone number of customer account",
            example = "082351252122"
    )
    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phone;

    @Valid
    private UpdateAccountRequest account;

}
