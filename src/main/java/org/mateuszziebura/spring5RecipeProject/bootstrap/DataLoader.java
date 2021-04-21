package org.mateuszziebura.spring5RecipeProject.bootstrap;

import org.mateuszziebura.spring5RecipeProject.domain.*;
import org.mateuszziebura.spring5RecipeProject.repositories.CategoryRepositories;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepositories;
import org.mateuszziebura.spring5RecipeProject.repositories.UnitOfMeasureRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UnitOfMeasureRepositories unitOfMeasureRepositories;
    private final RecipeRepositories recipeRepositories;
    private final CategoryRepositories categoryRepositories;

    public DataLoader(UnitOfMeasureRepositories unitOfMeasureRepositories, RecipeRepositories recipeRepositories, CategoryRepositories categoryRepositories) {
        this.unitOfMeasureRepositories = unitOfMeasureRepositories;
        this.recipeRepositories = recipeRepositories;
        this.categoryRepositories = categoryRepositories;
    }

    @Override
    public void run(String... args) throws Exception {

        Recipe spiceGrilledChicken = new Recipe();
        spiceGrilledChicken.setCookTime(15);
        spiceGrilledChicken.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        spiceGrilledChicken.setDifficulty(Difficulty.MODERATE);
        spiceGrilledChicken.setPrepTime(20);
        spiceGrilledChicken.setServings(5);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(getIngredient(spiceGrilledChicken,2,"Tablespoon","ancho chili powder"));
        ingredients.add(getIngredient(spiceGrilledChicken,"Teaspoon","dried oregano"));
        ingredients.add(getIngredient(spiceGrilledChicken,"Teaspoon","dried cumin"));
        ingredients.add(getIngredient(spiceGrilledChicken,"Teaspoon","sugar"));
        ingredients.add(getIngredient(spiceGrilledChicken,0.5,"Teaspoon","salt"));
        ingredients.add(getIngredient(spiceGrilledChicken,null,"clove garlic, finely chopped"));
        ingredients.add(getIngredient(spiceGrilledChicken,"Tablespoon","finely grated orange zest"));
        ingredients.add(getIngredient(spiceGrilledChicken,3,"Tablespoon","fresh-squeezed orange juice"));
        ingredients.add(getIngredient(spiceGrilledChicken,2,"Tablespoon","olive oil"));
        ingredients.add(getIngredient(spiceGrilledChicken,5,null,"skinless, boneless chicken thighs (1 1/4 pounds)"));
        spiceGrilledChicken.setIngredients(ingredients);
        Notes notes = new Notes();
        notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        spiceGrilledChicken.setNotes(notes);
        Category category1 = new Category();
        category1.setDescription("Healthy");
        spiceGrilledChicken.setUrl("Chicken");

        Category category2 = new Category();
        category2.setDescription("Dinners");
        categoryRepositories.save(category1);
        spiceGrilledChicken.getCategories().add(category1);
        categoryRepositories.save(category2);
        spiceGrilledChicken.getCategories().add(category2);
        spiceGrilledChicken.setSource("Spicy Grilled Chicken Tacos");
        recipeRepositories.save(spiceGrilledChicken);


        Recipe guacamole = new Recipe();
        guacamole.setUrl("Guacamole");
        guacamole.setCookTime(2);
        guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setPrepTime(10);
        guacamole.setServings(3);
        ingredients = new HashSet<>();
        ingredients.add(getIngredient(guacamole,2,null,"ripe avocados"));
        ingredients.add(getIngredient(guacamole,0.25,"Teaspoon","fresh lime juice or lemon juice"));
        ingredients.add(getIngredient(guacamole,"Tablespoon","dried cumin"));
        ingredients.add(getIngredient(guacamole,2,"Tablespoon","of minced red onion or thinly sliced green onion"));
        ingredients.add(getIngredient(guacamole,0.5,null,"serrano chiles, stems and seeds removed, minced"));
        ingredients.add(getIngredient(guacamole,2,"Tablespoon","cilantro (leaves and tender stems), finely chopped"));
        ingredients.add(getIngredient(guacamole,"Dash","of freshly grated black pepper"));
        ingredients.add(getIngredient(guacamole,0.5,"Tablespoon","ripe tomato, seeds and pulp removed, chopped"));
        ingredients.add(getIngredient(guacamole,null,"Red radishes or jicama, to garnish"));
        ingredients.add(getIngredient(guacamole,null,"Tortilla chips, to serve"));
        guacamole.setIngredients(ingredients);
        notes = new Notes();
        notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        guacamole.setNotes(notes);

        guacamole.setSource("How to Make Perfect Guacamole");

        recipeRepositories.save(guacamole);
    }

    private Ingredient getIngredient(Recipe recipe,double amount,String name, String description){
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(description);
        if(name!=null){
            Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepositories.findByDescription(name);
            ingredient.setUnitOfMeasure(unitOfMeasure.get());
        }
            ingredient.setAmount(BigDecimal.valueOf(amount));
        ingredient.setRecipe(recipe);
        return ingredient;
    }
    private Ingredient getIngredient(Recipe recipe,String name, String description){
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(description);
        if(name!=null){
            ingredient.setUnitOfMeasure(unitOfMeasureRepositories.findByDescription(name).get());
        }
        ingredient.setRecipe(recipe);
        return ingredient;
    }
}
