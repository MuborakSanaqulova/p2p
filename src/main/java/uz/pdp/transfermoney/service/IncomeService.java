package uz.pdp.transfermoney.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.transfermoney.entity.Income;
import uz.pdp.transfermoney.payload.IncomeDto;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IncomeService {
    Page<IncomeDto> findAll(Pageable pageable);

    Page<Income> findAllEntity(Pageable pageable);

    Optional<IncomeDto> finById(Long id);

    Optional<Income> findByIdEntity(Long id);

    Page<IncomeDto> findAllByUserId(Long userId, Pageable pageable);

    Page<Income> findAllByUserIdEntity(Long userId, Pageable pageable);

    Income save(Income income);
}
