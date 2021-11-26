package uz.pdp.transfermoney.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Card;
import uz.pdp.transfermoney.entity.Income;
import uz.pdp.transfermoney.entity.Outcome;
import uz.pdp.transfermoney.payload.OutcomeDto;
import uz.pdp.transfermoney.repository.OutcomeRepository;
import uz.pdp.transfermoney.service.CardService;
import uz.pdp.transfermoney.service.IncomeService;
import uz.pdp.transfermoney.service.OutcomeService;
import uz.pdp.transfermoney.service.mapper.IncomeMapper;
import uz.pdp.transfermoney.service.mapper.OutcomeMapper;

import java.util.Optional;

@Service
public class OutcomeImplService implements OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    OutcomeMapper outcomeMapper;

    @Autowired
    CardService cardService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    IncomeMapper incomeMapper;

    @Override
    public Optional<OutcomeDto> sendTo(OutcomeDto outcomeDto, Card fromCard, Card toCard) {

        fromCard.setBalance(fromCard.getBalance() - outcomeDto.getAmount() - outcomeDto.getAmount()*outcomeDto.getCommissionAmount());
        toCard.setBalance(toCard.getBalance()+outcomeDto.getAmount());

        Card fromCardSave = cardService.save(fromCard);
        Card toCardSave = cardService.save(toCard);

        Outcome outcome = outcomeMapper.toEntity(outcomeDto);
        outcome.setFromCard(fromCardSave);
        outcome.setToCard(toCardSave);
        outcome.setUserId(fromCard.getUserId());

        Income income = incomeMapper.outcomeToIncome(outcome);

        incomeService.save(income);

        return Optional.of(outcomeMapper.toDto(save(outcome)));

    }

    @Override
    public Outcome save(Outcome outcome) {
       return outcomeRepository.save(outcome);
    }


    @Override
    public Page<OutcomeDto> findOutcomesByUserId(Long userId, Pageable pageable) {
       return findOutcomesByUserIdEntity(userId, pageable).map(outcomeMapper::toDto);
    }

    @Override
    public Page<Outcome> findOutcomesByUserIdEntity(Long userId, Pageable pageable) {
       return outcomeRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Optional<OutcomeDto> findOutcomeByUserId(Long id, Long userId) {
       return findOutcomeByUserIdEntity(id, userId).map(outcomeMapper::toDto);
    }

    @Override
    public Optional<Outcome> findOutcomeByUserIdEntity(Long id, Long userId) {
       return outcomeRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Page<OutcomeDto> findAll(Pageable pageable) {
        return findAllEntity(pageable).map(outcomeMapper::toDto);
    }

    @Override
    public Page<Outcome> findAllEntity(Pageable pageable) {
        return outcomeRepository.findAll(pageable);
    }

    @Override
    public Optional<OutcomeDto> findByOutcomeId(Long id) {
       return findByOutcomeIdEntity(id).map(outcomeMapper::toDto);
    }

    @Override
    public Optional<Outcome> findByOutcomeIdEntity(Long id) {
        return outcomeRepository.findById(id);
    }
}
