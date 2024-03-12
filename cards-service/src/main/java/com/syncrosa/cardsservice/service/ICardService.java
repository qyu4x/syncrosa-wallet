package com.syncrosa.cardsservice.service;

import com.syncrosa.cardsservice.payload.request.CreateCardRequest;
import com.syncrosa.cardsservice.payload.request.UpdatedCardRequest;
import com.syncrosa.cardsservice.payload.response.CardResponse;

public interface ICardService {

    CardResponse create(CreateCardRequest request, String phone);

    CardResponse findByPhone(String phone);

    CardResponse updateByNumber(UpdatedCardRequest request, Long number);

    void deleteByPhone(String phone);

}
