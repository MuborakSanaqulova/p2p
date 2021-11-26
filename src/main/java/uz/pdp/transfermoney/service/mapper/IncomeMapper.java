package uz.pdp.transfermoney.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Income;
import uz.pdp.transfermoney.entity.Outcome;
import uz.pdp.transfermoney.payload.IncomeDto;

import java.time.LocalDateTime;

@Service
public class IncomeMapper {

    @Autowired
    CardMapper cardMapper;

    public IncomeDto toDto(Income income){
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setId(income.getId());
        incomeDto.setDate(income.getDate());
        incomeDto.setAmount(income.getAmount());
        incomeDto.setUserId(income.getUserId());
        incomeDto.setFromCard(cardMapper.toDto(income.getFromCard()));
        incomeDto.setToCard(cardMapper.toDto(income.getToCard()));
        return incomeDto;
    }

    public Income toEntity(IncomeDto incomeDto){
        Income income = new Income();
        income.setAmount(incomeDto.getAmount());
        income.setDate(LocalDateTime.now());
        income.setUserId(incomeDto.getUserId());
        return income;
    }

    public Income outcomeToIncome(Outcome outcome){
        Income income = new Income();
        income.setUserId(outcome.getToCard().getUserId());
        income.setAmount(outcome.getAmount());
        income.setDate(outcome.getDate());
        income.setFromCard(outcome.getFromCard());
        income.setToCard(outcome.getToCard());
        return income;
    }

}
