package guru.springframework.services;


import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RecipeServiceTest {

    @Mock
    private RecipeCommandToRecipe objectCommandToObject;
    @Mock
    private RecipeToRecipeCommand objectToObjectCommand;
    @Mock
    RecipeRepository repository;

    @InjectMocks
    private RecipeService service;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //service= new RecipeServiceImpl(repository,objectCommandToObject,objectToObjectCommand);

    }

    @Test
    void getRecipes() {

        System.out.println("hello");
    }

    @Test
    void findById() {
    }
}