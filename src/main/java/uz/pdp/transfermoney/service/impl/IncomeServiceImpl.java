package uz.pdp.transfermoney.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Income;
import uz.pdp.transfermoney.payload.IncomeDto;
import uz.pdp.transfermoney.repository.IncomeRepository;
import uz.pdp.transfermoney.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Override
    public Page<IncomeDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Income> findAllEntity(Pageable pageable) {

    }
}
