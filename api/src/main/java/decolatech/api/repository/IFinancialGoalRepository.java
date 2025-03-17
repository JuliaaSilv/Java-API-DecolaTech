package decolatech.api.repository;

import decolatech.api.entity.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {
    FinancialGoal findByUserId(Long id);
}
