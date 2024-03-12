package com.syncrosa.cardsservice.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatedCardRequest {

    @Size(max = 15)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    @NotBlank
    private String phone;

    @NotBlank
    private String type;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal totalLimit;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amountUsed;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal availableAmount;

}
