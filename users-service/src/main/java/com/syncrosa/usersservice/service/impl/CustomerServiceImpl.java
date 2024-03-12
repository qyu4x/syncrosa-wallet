package com.syncrosa.usersservice.service.impl;

import com.syncrosa.usersservice.entity.Account;
import com.syncrosa.usersservice.entity.Customer;
import com.syncrosa.usersservice.payload.response.AccountResponse;
import com.syncrosa.usersservice.payload.response.CustomerDetailResponse;
import com.syncrosa.usersservice.payload.response.CustomerResponse;
import com.syncrosa.usersservice.repository.AccountRepository;
import com.syncrosa.usersservice.repository.CustomerRepository;
import com.syncrosa.usersservice.service.ICustomerService;
import com.syncrosa.usersservice.service.client.CardsFeignClient;
import com.syncrosa.usersservice.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailResponse findDetailByPhone(String phone) {
        Customer customer = customerRepository.findFirstByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        CustomerDetailResponse customerDetailResponse = this.toCustomerDetailResponse(customer);
        customerDetailResponse.setCard(cardsFeignClient.findByPhone(phone).getBody().getData());
        customerDetailResponse.setLoan(loansFeignClient.findByPhone(phone).getBody().getData());

        return customerDetailResponse;
    }


    private CustomerDetailResponse toCustomerDetailResponse(Customer customer) {
        return CustomerDetailResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .account(this.toAccountResponse(customer.getAccount()))
                .createdAt(Objects.isNull(customer.getCreatedAt()) ? 0L : customer.getCreatedAt().toEpochMilli())
                .updatedAt(Objects.isNull(customer.getUpdatedAt()) ? 0L : customer.getUpdatedAt().toEpochMilli())
                .build();
    }

    private AccountResponse toAccountResponse(Account account) {
        return AccountResponse.builder()
                .number(account.getNumber())
                .type(account.getType())
                .branchAddress(account.getBranchAddress())
                .createdAt(Objects.isNull(account.getCreatedAt()) ? 0L : account.getCreatedAt().toEpochMilli())
                .updatedAt(Objects.isNull(account.getUpdatedAt()) ? 0L : account.getUpdatedAt().toEpochMilli())
                .build();
    }
}
