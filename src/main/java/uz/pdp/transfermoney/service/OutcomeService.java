package uz.pdp.transfermoney.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.transfermoney.entity.Card;
import uz.pdp.transfermoney.entity.Outcome;
import uz.pdp.transfermoney.payload.OutcomeDto;

import java.util.Optional;

public interface OutcomeService {
    Optional<OutcomeDto> sendTo(OutcomeDto outcomeDto, Card fromCard, Card toCard);

    Outcome save(Outcome outcome);

    Page<OutcomeDto> findOutcomesByUserId(Long userId, Pageable pageable);

    Page<Outcome> findOutcomesByUserIdEntity(Long userId, Pageable pageable);

    Optional<OutcomeDto> findOutcomeByUserId(Long id, Long userId);

    Optional<Outcome> findOutcomeByUserIdEntity(Long id, Long userId);

    Page<OutcomeDto> findAll(Pageable pageable);

    Page<Outcome> findAllEntity(Pageable pageable);

    Optional<OutcomeDto> findByOutcomeId(Long id);

    Optional<Outcome> findByOutcomeIdEntity(Long id);
}
