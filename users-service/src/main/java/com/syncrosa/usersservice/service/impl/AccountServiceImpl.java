package com.syncrosa.usersservice.service.impl;

import com.syncrosa.usersservice.entity.Account;
import com.syncrosa.usersservice.entity.Customer;
import com.syncrosa.usersservice.helper.GenerateRandomID;
import com.syncrosa.usersservice.payload.request.CreateCustomerRequest;
import com.syncrosa.usersservice.payload.request.UpdateCustomerRequest;
import com.syncrosa.usersservice.payload.response.AccountResponse;
import com.syncrosa.usersservice.payload.response.CustomerResponse;
import com.syncrosa.usersservice.repository.AccountRepository;
import com.syncrosa.usersservice.repository.CustomerRepository;
import com.syncrosa.usersservice.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;

    private AccountRepository accountRepository;

    private ValidationService validationService;

    @Override
    @Transactional
    public CustomerResponse create(CreateCustomerRequest request) {
        validationService.validate(request);

        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already registered");
        }

        Customer customer = new Customer();
        customer.setId(GenerateRandomID.generateRandomStringId(11));
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Account account = new Account();
        account.setNumber(this.generateRandomNumber());
        account.setType(request.getAccount().getType());
        account.setBranchAddress(request.getAccount().getBranchAddress());

        account.setCustomer(customer);

        customerRepository.save(customer);
        accountRepository.save(account);

        customer.setAccount(account);

        return this.toCustomerResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse findByPhone(String phone) {
        Customer customer = customerRepository.findFirstByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return this.toCustomerResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse update(UpdateCustomerRequest request, Long number) {
        validationService.validate(request);

        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (!customerRepository.findByPhone(request.getPhone()).stream()
                .filter(customer -> !customer.getAccount().getNumber().equals(number))
                .collect(Collectors.toList()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already registered");
        }

        account.setType(request.getAccount().getType());
        account.setBranchAddress(request.getAccount().getBranchAddress());

        account.getCustomer().setName(request.getName());
        account.getCustomer().setEmail(request.getEmail());
        account.getCustomer().setPhone(request.getPhone());

        accountRepository.save(account);
        return this.toCustomerResponse(account.getCustomer());
    }

    @Override
    @Transactional
    public void delete(String phone) {
        Customer customer = customerRepository.findFirstByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        accountRepository.delete(customer.getAccount());
        customerRepository.delete(customer);
    }

    private Long generateRandomNumber() {
        return 10000000000L + new Random().nextInt(900000000);
    }

    private CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
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
