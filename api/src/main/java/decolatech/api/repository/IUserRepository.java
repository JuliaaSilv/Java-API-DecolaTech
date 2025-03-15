package decolatech.api.repository;

import decolatech.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean existsByCpf(String cpf);
}
