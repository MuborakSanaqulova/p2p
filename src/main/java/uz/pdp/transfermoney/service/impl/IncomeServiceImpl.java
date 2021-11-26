package uz.pdp.transfermoney.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Income;
import uz.pdp.transfermoney.payload.IncomeDto;
import uz.pdp.transfermoney.repository.IncomeRepository;
import uz.pdp.transfermoney.service.IncomeService;
import uz.pdp.transfermoney.service.mapper.IncomeMapper;

import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    IncomeMapper incomeMapper;

    @Override
    public Page<IncomeDto> findAll(Pageable pageable) {
        return findAllEntity(pageable).map(incomeMapper::toDto);
    }

    @Override
    public Page<Income> findAllEntity(Pageable pageable) {
       return incomeRepository.findAll(pageable);
    }

    @Override
    public Optional<IncomeDto> finById(Long id) {
        return findByIdEntity(id).map(incomeMapper::toDto);
    }

    @Override
    public Optional<Income> findByIdEntity(Long id) {
        return incomeRepository.findById(id);
    }

    @Override
    public Page<IncomeDto> findAllByUserId(Long userId, Pageable pageable) {
        return findAllByUserIdEntity(userId, pageable).map(incomeMapper::toDto);
    }

    @Override
    public Page<Income> findAllByUserIdEntity(Long userId, Pageable pageable) {
       return incomeRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }


}
