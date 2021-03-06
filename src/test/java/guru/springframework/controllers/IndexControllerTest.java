package guru.springframework.controllers;

import guru.springframework.domain.Notes;
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


import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    Model model;
    @Mock
    RecipeService recipeService;
    private IndexController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception
    {
        MockMvc mockMvc=  MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("index"));
    }
    @Test
    public void getIndexPage() {

        //given
        Set<Recipe> recipes= new HashSet<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();

        recipe1.setId(1L);
        recipe2.setId(2L);

        recipes.add(recipe1);
        recipes.add(recipe2);

        //when
        when(recipeService.getAll()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor =ArgumentCaptor.forClass(Set.class);

        //then
        assertEquals("index", controller.getIndexPage(model));

        verify(recipeService, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setIdxController=argumentCaptor.getValue();
        assertEquals(2,setIdxController.size());
    }
}