package com.syncrosa.usersservice.service.client;

import com.syncrosa.usersservice.payload.client.ApiResponse;
import com.syncrosa.usersservice.payload.client.LoanResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(path = "/api/v1/loans-service/loans", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse<LoanResponse>> findByPhone(@NotBlank @RequestParam(value = "phone", required = true) String phone);

}
