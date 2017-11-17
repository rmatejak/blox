package pl.gov.coi.blox.repository;

import pl.gov.coi.blox.model.CategoryEntity;

public interface CategoryRepository {

    CategoryEntity findByType (String type);

}
