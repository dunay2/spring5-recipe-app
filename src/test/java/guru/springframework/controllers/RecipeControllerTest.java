package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    Model model;
    @Mock
    RecipeService service;
    private RecipeController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(service);
    }

    @Test
    public void getPage() {

        //when
        Recipe recipe= (Recipe) Recipe.builder().id(1L).build();
        when (service.findById(anyLong())).thenReturn(recipe);

        ArgumentCaptor<Recipe> argumentCaptor =ArgumentCaptor.forClass(Recipe.class);

        //then
        assertEquals("recipe/show", controller.showById ("1",model));

        verify(service, times(1)).findById (anyLong());
        verify(model, times(1)).addAttribute(eq("recipe"),argumentCaptor.capture());
    }

    @Test
    public void getRecipe() throws Exception {
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(controller).build();

        Recipe recipe= (Recipe) Recipe.builder().id(1L).build();
        when (service.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show/"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("recipe/show"))
        .andExpect(model().attributeExists("recipe") );
    }
}

