package com.syncrosa.loansservice.service;

import com.syncrosa.loansservice.payload.request.CreateLoanRequest;
import com.syncrosa.loansservice.payload.request.UpdateLoanRequest;
import com.syncrosa.loansservice.payload.response.LoanResponse;

public interface ILoanService {

    LoanResponse create(CreateLoanRequest request, String phone);

    LoanResponse findByPhone(String phone);

    LoanResponse updateByNumber(UpdateLoanRequest request, Long number);

    void deleteByPhone(String phone);

}
