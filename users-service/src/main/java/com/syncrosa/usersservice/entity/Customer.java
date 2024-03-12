package com.syncrosa.usersservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends AuditableEntity{

    @Id
    private String id;

    private String name;

    private String email;

    private String phone;

    @OneToOne(mappedBy = "customer")
    private Account account;

}
