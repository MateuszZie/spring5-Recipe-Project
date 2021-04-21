package org.mateuszziebura.spring5RecipeProject.repositories;

import org.mateuszziebura.spring5RecipeProject.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepositories extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
