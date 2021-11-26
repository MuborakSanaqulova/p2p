package uz.pdp.transfermoney.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.transfermoney.entity.Income;
import uz.pdp.transfermoney.payload.IncomeDto;

public interface IncomeService {
    Page<IncomeDto> findAll(Pageable pageable);

    Page<Income> findAllEntity(Pageable pageable);
}
