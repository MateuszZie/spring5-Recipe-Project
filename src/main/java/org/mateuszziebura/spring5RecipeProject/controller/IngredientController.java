package org.mateuszziebura.spring5RecipeProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.mateuszziebura.spring5RecipeProject.commands.IngredientCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.services.IngredientService;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.mateuszziebura.spring5RecipeProject.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @RequestMapping("recipe/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String id,
                                         @RequestParam String check, Model model){
        model.addAttribute("recipe", recipeService.findCommandByUrl(check));
        model.addAttribute("ingredient", ingredientService.findByRecipeUrlAndIngredientId(check, Long.valueOf(id)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @RequestMapping("recipe/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        Recipe recipe = recipeService.findById(savedCommand.getRecipeId());

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/ingredient/" + savedCommand.getId() + "/show?check=" +recipe.getUrl();
    }
}
