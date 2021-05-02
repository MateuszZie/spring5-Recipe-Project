package org.mateuszziebura.spring5RecipeProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.mateuszziebura.spring5RecipeProject.domain.Recipe;
import org.mateuszziebura.spring5RecipeProject.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeRepository recipeRepositories;

    public RecipeController(RecipeRepository recipeRepositories) {
        this.recipeRepositories = recipeRepositories;
    }

    @RequestMapping("recipe")
    public String recipe(@RequestParam String check, Model model){
        log.debug("log in recipe page " +check);
        Recipe recipe= recipeRepositories.findByUrl(check).get();
        model.addAttribute("recipe", recipe);
        try{
            model.addAttribute("total", recipe.getPrepTime()+recipe.getCookTime());
        }catch (NullPointerException e){
            model.addAttribute("total", "");
        }
        return "recipe/recipe";
    }
}
