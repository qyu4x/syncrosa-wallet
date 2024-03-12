package com.syncrosa.cardsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card extends AuditableEntity{

    @Id
    private String id;

    private String phone;

    private Long number;

    private String type;

    @Column(name = "total_limit")
    private BigDecimal totalLimit;

    @Column(name = "amount_used")
    private BigDecimal amountUsed;

    @Column(name = "available_amount")
    private BigDecimal availableAmount;


}
