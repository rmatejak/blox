package pl.gov.coi.blox.repository;

import pl.gov.coi.blox.model.ArticleEntity;

public interface ArticleRepository {

    ArticleEntity findById (Long id);

}
