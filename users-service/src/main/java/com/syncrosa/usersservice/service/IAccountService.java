package com.syncrosa.usersservice.service;

import com.syncrosa.usersservice.payload.request.CreateCustomerRequest;
import com.syncrosa.usersservice.payload.request.UpdateCustomerRequest;
import com.syncrosa.usersservice.payload.response.CustomerResponse;

public interface IAccountService {

    CustomerResponse create(CreateCustomerRequest request);

    CustomerResponse findByPhone(String phone);

    CustomerResponse update(UpdateCustomerRequest request, Long number);

    void delete(String phone);

    void updateEventStatus(Long accountNumber);
}
