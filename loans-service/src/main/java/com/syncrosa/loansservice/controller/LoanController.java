     package com.syncrosa.loansservice.controller;

import com.syncrosa.loansservice.payload.request.CreateLoanRequest;
import com.syncrosa.loansservice.payload.request.UpdateLoanRequest;
import com.syncrosa.loansservice.payload.response.LoanResponse;
import com.syncrosa.loansservice.payload.response.WebResponse;
import com.syncrosa.loansservice.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/loans-service/loans")
public class LoanController {

    private ILoanService loanService;

    @PostMapping(
            path = "/{phone}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<LoanResponse>> create(@Valid @RequestBody CreateLoanRequest request,
                                                             @NotBlank @PathVariable(value = "phone", required = true) String phone) {
        LoanResponse loanResponse = loanService.create(request, phone);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WebResponse.<LoanResponse>builder()
                        .statusCode(HttpStatus.CREATED.value()).message(HttpStatus.CREATED.getReasonPhrase())
                        .data(loanResponse).build());

    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<LoanResponse>> findByPhone(@NotBlank @RequestParam(value = "phone", required = true) String phone) {
        LoanResponse loanResponse = loanService.findByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<LoanResponse>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(loanResponse).build());

    }

    @PutMapping(
            path = "/{number}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<LoanResponse>> updateByNumber(@Valid @RequestBody UpdateLoanRequest request,
                                                                    @Min(0) @PathVariable(value = "number", required = true) Long number) {
        LoanResponse loanResponse = loanService.updateByNumber(request, number);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<LoanResponse>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(loanResponse).build());

    }

    @DeleteMapping(
            path = "/{phone}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<Boolean>> deleteByPhone(@NotBlank @PathVariable(value = "phone", required = true) String phone) {
        loanService.deleteByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<Boolean>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(true).build());

    }



}
