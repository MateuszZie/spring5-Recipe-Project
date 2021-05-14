package org.mateuszziebura.spring5RecipeProject.repositories;

import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {

   Optional<Recipe> findByUrl(String url);
}
