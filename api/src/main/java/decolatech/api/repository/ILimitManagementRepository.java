package decolatech.api.repository;

import decolatech.api.entity.LimitManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILimitManagementRepository extends JpaRepository<LimitManagement, Long> {
    LimitManagement findByUserId(Long id);
}
