package pl.gov.coi.blox.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gov.coi.blox.entity.BlogEntity;
import pl.gov.coi.blox.entity.UserEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    BlogEntity findById(Long id);
}
