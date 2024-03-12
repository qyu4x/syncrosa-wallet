package com.syncrosa.cardsservice.controller;

import com.syncrosa.cardsservice.payload.request.CreateCardRequest;
import com.syncrosa.cardsservice.payload.request.UpdatedCardRequest;
import com.syncrosa.cardsservice.payload.response.CardResponse;
import com.syncrosa.cardsservice.payload.response.WebResponse;
import com.syncrosa.cardsservice.service.ICardService;
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
@RequestMapping(path = "/api/v1/cards-service/cards")
public class CardController {

    private ICardService cardService;

    @PostMapping(
            path = "/{phone}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<CardResponse>> create(@Valid @RequestBody CreateCardRequest request,
                                                            @NotBlank @PathVariable(value = "phone", required = true) String phone) {
        CardResponse cardResponse = cardService.create(request, phone);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WebResponse.<CardResponse>builder()
                        .statusCode(HttpStatus.CREATED.value()).message(HttpStatus.CREATED.getReasonPhrase())
                        .data(cardResponse).build());
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<CardResponse>> findByPhone(@NotBlank @RequestParam(value = "phone", required = true) String phone) {
        CardResponse cardResponse = cardService.findByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<CardResponse>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(cardResponse).build());
    }


    @PutMapping(
            path = "/{number}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<CardResponse>> updateByNumber(@Valid @RequestBody UpdatedCardRequest request,
                                                            @Min(0) @PathVariable(value = "number", required = true) Long number) {
        CardResponse cardResponse = cardService.updateByNumber(request, number);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<CardResponse>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(cardResponse).build());
    }

    @DeleteMapping(
            path = "/{phone}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<Boolean>> deleteByPhone(@NotBlank @PathVariable(value = "phone", required = true) String phone) {
        cardService.deleteByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.<Boolean>builder()
                        .statusCode(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase())
                        .data(true).build());
    }
}
