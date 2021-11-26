package uz.pdp.transfermoney.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.transfermoney.entity.Card;
import uz.pdp.transfermoney.payload.CardDto;

import java.util.Optional;

public interface CardService {

    CardDto createCard(CardDto cardDto);

    Card save(Card card);

    Page<CardDto> findAllDto(Pageable pageable);

    Page<Card> findAll(Pageable pageable);

    Optional<CardDto> findOne(Long id);

    Optional<Card> findById(Long id);

    Page<CardDto> findCardsByUserId(Long userId, Pageable pageable);

    Page<Card> findCardsByUserIdEntity(Long userId, Pageable pageable);

    boolean canTransfer(Long cardId, Double amount);
}
