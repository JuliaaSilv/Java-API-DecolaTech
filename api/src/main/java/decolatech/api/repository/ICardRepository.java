package decolatech.api.repository;

import decolatech.api.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<Card, Long> {
    Card findByUserId(Long id);
}
