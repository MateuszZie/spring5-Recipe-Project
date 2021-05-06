package org.mateuszziebura.spring5RecipeProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.mateuszziebura.spring5RecipeProject.commands.RecipeCommand;
import org.mateuszziebura.spring5RecipeProject.converters.RecipeToRecipeCommand;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe")
    public String recipe(@RequestParam String check, Model model){
        log.debug("log in recipe page " +check);
        Recipe recipe= recipeService.findByUrl(check);
        model.addAttribute("recipe", recipe);
        try{
            model.addAttribute("total", recipe.getPrepTime()+recipe.getCookTime());
        }catch (NullPointerException e){
            model.addAttribute("total", "");
        }
        return "recipe/recipe";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @RequestMapping("recipe/update")
    public String update(@RequestParam String check, Model model){
        model.addAttribute("recipe", recipeService.findCommandByUrl(check));

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand saveRecipe = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe?check=" + saveRecipe.getUrl();
    }
}
