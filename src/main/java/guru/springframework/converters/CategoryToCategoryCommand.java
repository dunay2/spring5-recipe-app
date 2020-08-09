package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand {
    public CategoryCommand convert(Category category) {
        return null;
    }
}
