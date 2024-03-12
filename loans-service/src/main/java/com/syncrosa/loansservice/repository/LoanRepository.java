package com.syncrosa.loansservice.repository;

import com.syncrosa.loansservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

    Optional<Loan> findByPhone(String phone);

    Optional<Loan> findByNumber(Long number);

}
