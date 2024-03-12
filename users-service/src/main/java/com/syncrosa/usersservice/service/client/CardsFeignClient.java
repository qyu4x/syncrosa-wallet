package com.syncrosa.usersservice.service.client;

import com.syncrosa.usersservice.payload.client.ApiResponse;
import com.syncrosa.usersservice.payload.client.CardResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(path = "/api/v1/cards-service/cards", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse<CardResponse>> findByPhone(@NotBlank @RequestParam(value = "phone", required = true) String phone);

}
