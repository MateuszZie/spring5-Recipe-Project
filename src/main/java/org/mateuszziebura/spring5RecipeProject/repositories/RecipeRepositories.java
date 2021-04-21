package org.mateuszziebura.spring5RecipeProject.repositories;

import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepositories extends CrudRepository<Recipe, Long> {

   Recipe findByUrl(String url);
}
