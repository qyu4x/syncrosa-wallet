package com.syncrosa.cardsservice.repository;

import com.syncrosa.cardsservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

    Optional<Card> findByPhone(String phone);

    Optional<Card> findByNumber(Long number);

}
