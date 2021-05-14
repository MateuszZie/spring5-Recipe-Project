package org.mateuszziebura.spring5RecipeProject.repositories;

import org.mateuszziebura.spring5RecipeProject.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
