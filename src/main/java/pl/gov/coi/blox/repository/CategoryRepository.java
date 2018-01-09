package pl.gov.coi.blox.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gov.coi.blox.entity.CategoryEntity;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findById(Long id);
}
