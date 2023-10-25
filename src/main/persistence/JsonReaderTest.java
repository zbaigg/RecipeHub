//Source: This class is adapted from the JsonReaderTest class in the JsonSerializationDemo file.

package persistence;

import model.Recipe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeList() {
        JsonReader reader = new JsonReader("./data/testRecipesEmpty.json");
        try {
            List<Recipe> recipeList = reader.read();
            assertEquals(0, recipeList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyRecipeListThrowsException() {
        JsonReader reader = new JsonReader("./data/testRecipesEmxty.json");
        try {
            reader.read();
        } catch (IOException e) {
            //pass;
        }
    }

    @Test
    void testReaderRecipeGeneral() {
        JsonReader reader = new JsonReader("./data/testRecipesGeneral.json");
        try {
            List<Recipe> recipeList = reader.read();
            assertEquals(1, recipeList.size());
            List<String> dirs = new ArrayList<>();
            dirs.add("step1");
            dirs.add("step2");
            List<String> ings = new ArrayList<>();
            ings.add("Cocoa Powder");
            ings.add("Milk");
            checkRecipe("Hot chocolate", dirs, "15 minutes", ings, "Microwaveable", recipeList.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}