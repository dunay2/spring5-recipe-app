package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    Model model;
    @Mock
    RecipeService recipeService;
    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        //given

        Set<Recipe> recipes= new HashSet<>();
        Recipe recipe1= new Recipe();
        recipe1.setId(1L);
        recipes.add(recipe1);

        Recipe recipe2= new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe2);

        //when
        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor =ArgumentCaptor.forClass(Set.class);

        //then
        assertEquals("index", indexController.getIndexPage(model));

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setIdxController=argumentCaptor.getValue();
        assertEquals(2,setIdxController.size());
    }
}










