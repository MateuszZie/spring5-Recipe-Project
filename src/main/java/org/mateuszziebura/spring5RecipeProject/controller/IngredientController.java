package org.mateuszziebura.spring5RecipeProject.controller;

import lombok.extern.slf4j.Slf4j;
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

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/ingredients/show")
    public String listIngredients(@RequestParam String check, Model model){
        log.debug("Getting ingredient list for recipe url: " + check);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandByUrl(check));

        return "recipe/ingredient/list";
    }
}
