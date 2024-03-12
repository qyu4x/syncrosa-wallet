package com.syncrosa.loansservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "loans")
public class Loan extends AuditableEntity{

    @Id
    private String id;

    private String phone;

    private Long number;

    private String type;

    private BigDecimal total;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Column(name = "outstanding_amount")
    private BigDecimal outstandingAmount;


}
