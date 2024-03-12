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
public class CardResponse {

    private String id;

    private String phone;

    private Long number;

    private String type;

    private BigDecimal totalLimit;

    private BigDecimal amountUsed;

    private BigDecimal availableAmount;

    private Long createdAt;

    private Long updatedAt;

}
