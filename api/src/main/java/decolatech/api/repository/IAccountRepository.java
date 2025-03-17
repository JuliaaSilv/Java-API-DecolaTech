package decolatech.api.repository;

import decolatech.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByUserId(Long id);

}
