package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;


public interface RecipeService extends BaseService <RecipeCommand,Recipe> {
}
