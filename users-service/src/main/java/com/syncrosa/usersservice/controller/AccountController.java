package com.syncrosa.usersservice.controller;

import com.syncrosa.usersservice.payload.request.CreateCustomerRequest;
import com.syncrosa.usersservice.payload.request.UpdateCustomerRequest;
import com.syncrosa.usersservice.payload.response.CustomerResponse;
import com.syncrosa.usersservice.payload.response.ErrorResponse;
import com.syncrosa.usersservice.payload.response.WebResponse;
import com.syncrosa.usersservice.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts", description = "Perform CRUD operation in the Accounts")
@RestController
@AllArgsConstructor
@RequestMapping(
        path = "/api/v1/user-service/accounts"
)

@Validated
public class AccountController {

    private IAccountService accountService;

    @Operation(operationId = "create", summary = "Create new customer Account", description = "Create new customer Account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created new customer Account",
                            content = {
                                    @Content(mediaType = "application/json")
                            }
                    ),
                    @ApiResponse(responseCode = "409", description = "Conflict phone number already registered", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<CustomerResponse>> create(@Valid @RequestBody CreateCustomerRequest request) {
        CustomerResponse customerResponse = accountService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WebResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.CREATED.value()).message(HttpStatus.CREATED.getReasonPhrase())
                        .data(customerResponse).build());
    }

    @Operation(operationId = "findByPhone", summary = "Find customer by phone number", description = "Find customer by phone number")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found customer",
                            content = {
                                    @Content(mediaType = "application/json")
                            }
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid phone number supplied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Did not find any customer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @Parameters(
            @Parameter(in = ParameterIn.QUERY, name = "phone", description = "Customer Phone")
    )
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<CustomerResponse>> findByPhone(@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
                                                                     @RequestParam(value = "phone", required = true) String phone) {
        CustomerResponse customerResponse = accountService.findByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(customerResponse).build());
    }


    @Operation(operationId = "updateByNumber", summary = "Update customer by account number", description = "Update customer by account number")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated customer",
                            content = {
                                    @Content(mediaType = "application/json")
                            }
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Did not find any customer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @Parameters(
            @Parameter(in = ParameterIn.PATH, name = "number", description = "Account number")
    )
    @PutMapping(
            path = "/{number}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<CustomerResponse>> updateByNumber(@Valid @RequestBody UpdateCustomerRequest request,
                                                                        @PathVariable("number") Long number) {
        CustomerResponse customerResponse = accountService.update(request, number);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(customerResponse).build());
    }

    @Operation(operationId = "deleteByPhone", summary = "Delete customer by phone number", description = "Delete customer by phone number")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted customer"
                    ),
                    @ApiResponse(responseCode = "404", description = "Did not find any customer", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @Parameters(
            @Parameter(in = ParameterIn.QUERY, name = "phone", description = "Customer Phone")
    )
    @DeleteMapping(
            path = "/{phone}/customers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<Boolean>> deleteByNumber(@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
                                                               @PathVariable("phone") String phone) {
        accountService.delete(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<Boolean>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(true).build());
    }
}
