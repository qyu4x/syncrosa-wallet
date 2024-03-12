package com.syncrosa.usersservice.repository;

import com.syncrosa.usersservice.entity.Account;
import com.syncrosa.usersservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByPhone(String phone);

    Optional<Customer> findFirstByPhone(String phone);

    List<Customer> findByPhone(String phone);

    Optional<Customer> findByAccount(Account account);

}
