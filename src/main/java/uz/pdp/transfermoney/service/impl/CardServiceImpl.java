package uz.pdp.transfermoney.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Card;
import uz.pdp.transfermoney.payload.CardDto;
import uz.pdp.transfermoney.repository.CardRepository;
import uz.pdp.transfermoney.service.CardService;
import uz.pdp.transfermoney.service.mapper.CardMapper;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardMapper cardMapper;

    @Override
    public CardDto createCard(CardDto cardDto) {
        Card card = save(cardMapper.toEntity(cardDto));
        return cardMapper.toDto(card);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Page<CardDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(cardMapper::toDto);
    }

    @Override
    public Page<Card> findAll(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Override
    public Optional<CardDto> findOne(Long id) {
        return findById(id).map(cardMapper::toDto);
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Page<CardDto> findCardsByUserId(Long userId, Pageable pageable) {
        return findCardsByUserIdEntity(userId, pageable).map(cardMapper::toDto);
    }

    @Override
    public Page<Card> findCardsByUserIdEntity(Long userId, Pageable pageable) {
        return cardRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public boolean canTransfer(Long cardId, Double amount) {
        Optional<Card> card = findById(cardId);
        if (card.isEmpty() || (card.get().getBalance() - amount - amount * 0.0035) < 0)
            return false;
        return true;
    }
}
