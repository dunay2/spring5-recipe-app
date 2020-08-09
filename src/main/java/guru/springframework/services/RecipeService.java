package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;


public interface RecipeService extends DefaultService<RecipeCommand,Recipe> {
}
