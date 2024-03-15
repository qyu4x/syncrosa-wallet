package com.syncrosa.usersservice.controller;


import com.syncrosa.usersservice.payload.response.CustomerDetailResponse;
import com.syncrosa.usersservice.payload.response.CustomerResponse;
import com.syncrosa.usersservice.payload.response.ErrorResponse;
import com.syncrosa.usersservice.payload.response.WebResponse;
import com.syncrosa.usersservice.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts", description = "Perform CRUD operation in the Customer")
@RestController
@AllArgsConstructor
@RequestMapping(
        path = "/api/v1/user-service/customers"
)

@Validated
public class CustomerController {

    private final ICustomerService customerService;

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Operation(operationId = "findCustomerDetailByPhone", summary = "Find customer Detail by phone number", description = "Find customer detail by phone number")
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
    public ResponseEntity<WebResponse<CustomerDetailResponse>> findCustomerDetailByPhone(
            @RequestHeader("X-Syncrosa-Trace-Id") String traceId,
            @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
            @RequestParam(value = "phone", required = true) String phone) {
        log.debug("Request from: {} with traceId: {}", this.getClass(), traceId);
        CustomerDetailResponse customerDetailResponse = customerService.findDetailByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<CustomerDetailResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(customerDetailResponse)
                        .build());

    }

}
