package uz.pdp.transfermoney.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Outcome;
import uz.pdp.transfermoney.payload.OutcomeDto;

import java.time.LocalDateTime;

@Service
public class OutcomeMapper {

    @Autowired
    CardMapper cardMapper;

    public OutcomeDto toDto(Outcome outcome) {
        OutcomeDto outcomeDto = new OutcomeDto();
        outcomeDto.setId(outcome.getId());
        outcomeDto.setFromCard(cardMapper.toDto(outcome.getFromCard()));
        outcomeDto.setToCard(cardMapper.toDto(outcome.getToCard()));
        outcomeDto.setAmount(outcome.getAmount());
        outcomeDto.setDate(outcome.getDate());
        outcomeDto.setCommissionAmount(outcome.getCommissionAmount());
        outcomeDto.setUserId(outcome.getUserId());
        return outcomeDto;
    }

    public Outcome toEntity(OutcomeDto outcomeDto){
        Outcome outcome = new Outcome();
        outcome.setDate(LocalDateTime.now());
        outcome.setAmount(outcomeDto.getAmount());
        outcome.setCommissionAmount(outcomeDto.getCommissionAmount());
        return outcome;
    }

}
