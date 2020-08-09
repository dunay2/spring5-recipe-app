package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService  {

    @Autowired
    private RecipeRepository repository;
    @Autowired
    private  RecipeCommandToRecipe objectCommandToObject;
    @Autowired
    private  RecipeToRecipeCommand objectToObjectCommand;

    public Set<Recipe> getAll() {
        log.debug("call from recipe service impl ");

        Set<Recipe> recipeSet = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Transactional
    public RecipeCommand saveCommand(RecipeCommand command) {
        Recipe detachedRecipe = objectCommandToObject.convert(command);

        Recipe savedRecipe=repository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return objectToObjectCommand.convert(savedRecipe);
    }

    @Transactional
    public RecipeCommand findCommandById(Long l) {
          return objectToObjectCommand.convert(findById(l));
    }

    public Recipe findById(Long l) {
        Optional<Recipe> recipe= repository.findById(l);

        if(!recipe.isPresent()) {
            throw new RuntimeException("Recipe not found!");
        }
        return recipe.get();
    }
}