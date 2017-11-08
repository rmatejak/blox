package pl.gov.coi.blox.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gov.coi.blox.api.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
