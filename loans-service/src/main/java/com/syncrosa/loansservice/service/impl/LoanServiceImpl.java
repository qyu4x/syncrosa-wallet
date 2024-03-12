package com.syncrosa.loansservice.service.impl;

import com.syncrosa.loansservice.entity.Loan;
import com.syncrosa.loansservice.helper.GenerateRandomID;
import com.syncrosa.loansservice.payload.request.CreateLoanRequest;
import com.syncrosa.loansservice.payload.request.UpdateLoanRequest;
import com.syncrosa.loansservice.payload.response.LoanResponse;
import com.syncrosa.loansservice.repository.LoanRepository;
import com.syncrosa.loansservice.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    @Transactional
    public LoanResponse create(CreateLoanRequest request, String phone) {
        loanRepository.findByPhone(phone)
                .ifPresent(loan -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already registered");
                });
        Loan loan = new Loan();
        loan.setId(GenerateRandomID.generateRandomStringId(11));
        loan.setNumber(10000000000L + new Random().nextInt(900000000));
        loan.setPhone(request.getPhone());
        loan.setType(request.getType());
        loan.setTotal(request.getTotal());
        loan.setAmountPaid(BigDecimal.ZERO);
        loan.setOutstandingAmount(BigDecimal.ZERO);

        loanRepository.save(loan);
        return this.toLoanResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse findByPhone(String phone) {
        Loan loan = loanRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));
        return this.toLoanResponse(loan);
    }

    @Override
    @Transactional
    public LoanResponse updateByNumber(UpdateLoanRequest request, Long number) {
        Loan loan = loanRepository.findByNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));
        loan.setPhone(loan.getPhone());
        loan.setType(request.getType());
        loan.setTotal(request.getTotal());
        loan.setAmountPaid(request.getAmountPaid());
        loan.setOutstandingAmount(request.getOutstandingAmount());

        loanRepository.save(loan);
        return this.toLoanResponse(loan);
    }

    @Override
    @Transactional
    public void deleteByPhone(String phone) {
        Loan loan = loanRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));
        loanRepository.delete(loan);
    }

    private LoanResponse toLoanResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getId())
                .phone(loan.getPhone())
                .number(loan.getNumber())
                .type(loan.getType())
                .total(loan.getTotal())
                .amountPaid(loan.getAmountPaid())
                .outstandingAmount(loan.getOutstandingAmount())
                .createdAt(Objects.nonNull(loan.getCreatedAt()) ? loan.getCreatedAt().toEpochMilli() : 0L)
                .updatedAt(Objects.nonNull(loan.getUpdatedAt()) ? loan.getUpdatedAt().toEpochMilli() : 0L)
                .build();
    }
}
