package decolatech.api.repository;

import decolatech.api.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository<News, Long> {
}
