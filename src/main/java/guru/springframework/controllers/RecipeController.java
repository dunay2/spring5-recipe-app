package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    private  RecipeService service;

    public RecipeController(RecipeService recipeService) {
        this.service = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id,Model model) {
        log.debug("call getShowPage on RecipeController goes to recipe/show");
        model.addAttribute("recipe", service.findById(Long.valueOf(id)) );

        return "recipe/show";
    }


}



