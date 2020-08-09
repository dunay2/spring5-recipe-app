package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by jt on 6/1/17.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private RecipeService recipeService;

    /*public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }*/

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model)
    {
        log.debug("call inside index controller");
        model.addAttribute("recipes", recipeService.getAll());
        return "index";
    }


}
