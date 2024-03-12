package com.syncrosa.usersservice.payload.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {

    private String id;

    private String phone;

    private Long number;

    private String type;

    private BigDecimal total;

    private BigDecimal amountPaid;

    private BigDecimal outstandingAmount;

    private Long createdAt;

    private Long updatedAt;

}
