package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
public class RecipeBootstrap  implements ApplicationListener <ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList(2);
        //get UOMS
        Optional<UnitOfMeasure> eachUomOption = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found Each");
        }

        Optional<UnitOfMeasure> tableSpoonUomOption = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found Tablespoon");
        }

        Optional<UnitOfMeasure> teaSpoonUomOption = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found Teaspoon");
        }

        Optional<UnitOfMeasure> dashUomOption = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found Dash");
        }

        Optional<UnitOfMeasure> pintUomOption = unitOfMeasureRepository.findByDescription("Pint");

        if (!pintUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found Pint");
        }

        Optional<UnitOfMeasure> cupUomOption = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUomOption.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found Cup");
        }

        //get UOM optionals
        UnitOfMeasure eachUom = eachUomOption.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOption.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOption.get();
        UnitOfMeasure dashUom = dashUomOption.get();
        UnitOfMeasure pintUom = pintUomOption.get();
        UnitOfMeasure cupsUom = cupUomOption.get();

        //get categories optionals

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Category not found");
        }


        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Recipe
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the" +
                " inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel" +
                " an Avocado.) Place in a bowl." + "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" + "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
        );

        Notes guacNotes= new Notes();

        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);

        log.info("boot strap guacamole created");

        guacRecipe.addIngredient(Ingredient.builder().description("ripe avocados").amount(new BigDecimal(2)).uom(eachUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("Kosher salt").amount(new BigDecimal(5)).uom(teaSpoonUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("fresh lime juice or lemon juice").amount(new BigDecimal(2)).uom(tableSpoonUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("minced red onion or thinly sliced green onion").amount(new BigDecimal(2)).uom(tableSpoonUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("serrano chiles, stems and seeds removed, minced").amount(new BigDecimal(2)).uom(eachUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("Cilantro").amount(new BigDecimal(2)).uom(tableSpoonUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("freshly grated black pepper").amount(new BigDecimal(2)).uom(dashUom).build());
        guacRecipe.addIngredient(Ingredient.builder().description("ripe tomato, seeds and pulp removed, chopped").amount(new BigDecimal(5)).uom(eachUom).build());



       guacRecipe.addCategory(americanCategory);
       guacRecipe.addCategory (mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);



        tacosRecipe.addIngredient(Ingredient.builder()
                    .description("Ancho Chili Powde")
                    .amount(new BigDecimal(2))
                    .uom(tableSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Dried Oregano")
                .amount(new BigDecimal(1))
                .uom(teaSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Dried Cumin")
                .amount(new BigDecimal(1))
                .uom(teaSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Sugar")
                .amount(new BigDecimal(1))
                .uom(teaSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Salt")
                .amount(new BigDecimal(".5"))
                .uom(teaSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Clove of Garlic, Choppedr")
                .amount(new BigDecimal(1))
                .uom(eachUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("finely grated orange zestr")
                .amount(new BigDecimal(1))
                .uom(tableSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("fresh-squeezed orange juice")
                .amount(new BigDecimal(3))
                .uom(tableSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Olive Oil")
                .amount(new BigDecimal(2))
                .uom(tableSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("boneless chicken thighs")
                .amount(new BigDecimal(4))
                .uom(tableSpoonUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("small corn tortillas")
                .amount(new BigDecimal(8))
                .uom(eachUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("packed baby arugula")
                .amount(new BigDecimal(3))
                .uom(cupsUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("medium ripe avocados, slic")
                .amount(new BigDecimal(2))
                .uom(eachUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("radishes, thinly sliced")
                .amount(new BigDecimal(4))
                .uom(eachUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("cherry tomatoes, halved")
                .amount(new BigDecimal(5))
                .uom(pintUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("red onion, thinly slice")
                .amount(new BigDecimal(".25"))
                .uom(eachUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("Roughly chopped cilantro")
                .amount(new BigDecimal(4))
                .uom(eachUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("cup sour cream thinned with 1/4 cup milk")
                .amount(new BigDecimal(4))
                .uom(cupsUom).build());

        tacosRecipe.addIngredient(Ingredient.builder()
                .description("lime, cut into wedges")
                .amount(new BigDecimal(4))
                .uom(eachUom).build());

        tacosRecipe.addCategory(americanCategory);
        tacosRecipe.addCategory(mexicanCategory);

        recipes.add(tacosRecipe);

        log.info("boot strap taco created");

        return recipes;
    }

}