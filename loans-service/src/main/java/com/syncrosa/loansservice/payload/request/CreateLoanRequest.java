package com.syncrosa.loansservice.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoanRequest {

    @Size(min = 10, max = 15)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    @NotBlank
    private String phone;

    @NotBlank
    private String type;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal total;

}
