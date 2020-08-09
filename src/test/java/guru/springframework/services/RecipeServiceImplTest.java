package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl service;

    private RecipeCommandToRecipe objectCommandToObject;
    private RecipeToRecipeCommand objectToObjectCommand;

    @Mock
    RecipeRepository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service= new RecipeServiceImpl(repository,objectCommandToObject,objectToObjectCommand);
    }

    @Test
    public void getRecipes() {
        Recipe recipe= new Recipe();
        HashSet data = new HashSet<String>();
        data.add(recipe);

        when (repository.findAll()).thenReturn(data);

        Set<Recipe> recipes=service.getAll();
        assertEquals(recipes.size(),1);
        verify(repository,times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        Optional<Recipe> optionalRecipe= Optional.of( Recipe.builder().id(1L).build());

        when (repository.findById (anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned=service.findById(1L);

        assertNotNull("Null recipe Returned",recipeReturned);

        verify(repository,times(1)).findById (anyLong());
        verify(repository,never()).findAll ();
    }

}