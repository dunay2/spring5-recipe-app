package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand {
    public IngredientCommand convert(Ingredient ingredient) {
        return null;
    }
}
