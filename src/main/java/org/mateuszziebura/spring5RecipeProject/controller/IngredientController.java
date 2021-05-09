package org.mateuszziebura.spring5RecipeProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.mateuszziebura.spring5RecipeProject.services.IngredientService;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipe/ingredients/show")
    public String listIngredients(@RequestParam String check, Model model){
        log.debug("Getting ingredient list for recipe url: " + check);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandByUrl(check));

        return "recipe/ingredient/list";
    }

    @RequestMapping("recipe/ingredient/{recipeId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @RequestParam String check, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeUrlAndIngredientId(check, Long.valueOf(recipeId)));
        return "recipe/ingredient/show";
    }
}
