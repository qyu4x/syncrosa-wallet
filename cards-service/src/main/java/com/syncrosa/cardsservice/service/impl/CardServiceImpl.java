package com.syncrosa.cardsservice.service.impl;

import com.syncrosa.cardsservice.entity.Card;
import com.syncrosa.cardsservice.helper.GenerateRandomID;
import com.syncrosa.cardsservice.payload.request.CreateCardRequest;
import com.syncrosa.cardsservice.payload.request.UpdatedCardRequest;
import com.syncrosa.cardsservice.payload.response.CardResponse;
import com.syncrosa.cardsservice.repository.CardRepository;
import com.syncrosa.cardsservice.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;

    @Override
    @Transactional
    public CardResponse create(CreateCardRequest request, String phone) {
        cardRepository.findByPhone(phone)
                .ifPresent(loan -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already registered");
                });
        Card card = new Card();
        card.setId(GenerateRandomID.generateRandomStringId(11));
        card.setPhone(request.getPhone());
        card.setNumber(10000000000L + new Random().nextInt(900000000));
        card.setType(request.getType());
        card.setTotalLimit(BigDecimal.valueOf(100000000L));
        card.setAmountUsed(BigDecimal.valueOf(0));
        card.setAvailableAmount(BigDecimal.valueOf(0));

        cardRepository.save(card);
        return this.toCardResponse(card);
    }

    @Override
    @Transactional
    public CardResponse findByPhone(String phone) {
        Card card = cardRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
        return this.toCardResponse(card);
    }

    @Override
    @Transactional
    public CardResponse updateByNumber(UpdatedCardRequest request, Long number) {
        Card card = cardRepository.findByNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
        card.setPhone(request.getPhone());
        card.setType(request.getType());
        card.setTotalLimit(request.getTotalLimit());
        card.setAmountUsed(request.getAmountUsed());
        card.setAvailableAmount(request.getAvailableAmount());

        cardRepository.save(card);
        return this.toCardResponse(card);
    }

    @Override
    @Transactional
    public void deleteByPhone(String phone) {
        Card card = cardRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
        cardRepository.delete(card);
    }

    private CardResponse toCardResponse(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .phone(card.getPhone())
                .number(card.getNumber())
                .type(card.getType())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .createdAt(Objects.nonNull(card.getCreatedAt()) ? card.getCreatedAt().toEpochMilli() : 0L)
                .updatedAt(Objects.nonNull(card.getUpdatedAt()) ? card.getUpdatedAt().toEpochMilli() : 0L)
                .build();
    }
}
