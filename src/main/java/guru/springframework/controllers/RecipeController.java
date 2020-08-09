package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    private RecipeService service;

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        log.debug("call getShowPage on RecipeController goes to recipe/show");
        model.addAttribute("recipe", service.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
    RecipeCommand savedCommand = service.saveCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", service.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }

}