package persistence;

import model.Recipe;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe(String name, List<String> dirs, String time, List<String> ings, String cgry, Recipe r) {
        assertEquals(name, r.getName());
        assertEquals(dirs, r.getDirections());
        assertEquals(time, r.getTime());
        assertEquals(ings, r.getIngredients());
        assertEquals(cgry, r.getCategory());

    }
}
