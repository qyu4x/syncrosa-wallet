package com.syncrosa.usersservice.service;

import com.syncrosa.usersservice.payload.response.CustomerDetailResponse;

public interface ICustomerService {

    CustomerDetailResponse findDetailByPhone(String phone);

}
