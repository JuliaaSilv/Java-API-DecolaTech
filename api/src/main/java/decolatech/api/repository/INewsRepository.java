package decolatech.api.repository;

import decolatech.api.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INewsRepository extends JpaRepository<News, Long> {
    List<News> findByUserId(Long id);
}
