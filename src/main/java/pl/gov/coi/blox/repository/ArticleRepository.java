package pl.gov.coi.blox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gov.coi.blox.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>{
    ArticleEntity findById(Long id);
}
