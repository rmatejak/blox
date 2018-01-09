package pl.gov.coi.blox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gov.coi.blox.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
    CommentEntity findById(Long id);
}
