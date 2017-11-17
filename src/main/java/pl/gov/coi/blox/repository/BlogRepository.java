package pl.gov.coi.blox.repository;

import pl.gov.coi.blox.model.BlogEntity;

public interface BlogRepository {

    BlogEntity findById (Long id);

}
