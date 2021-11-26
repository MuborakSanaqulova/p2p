package uz.pdp.transfermoney.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.transfermoney.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
        Page<Income> findAllByUserId(Long userId, Pageable pageable);
}