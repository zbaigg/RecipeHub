package persistence;

import model.Recipe;

import org.junit.jupiter.api.Test;
import ui.CookBookApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRecipe() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeList.json");
            writer.open();
            writer.write(new CookBookApp(true).toJson());
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeList.json");
            List<Recipe> recipeList = reader.read();
            assertEquals(0, recipeList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipeList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            CookBookApp app = new CookBookApp(true);
            List<String> dirs = new ArrayList<>();
            dirs.add("step 1");
            dirs.add("step 2");
            List<String> ings = new ArrayList<>();
            ings.add("cocoa");
            ings.add("milk");
            app.addRecipe("Hot chocolate", dirs, "5 mins", ings, "microwaveable");
            writer.write(app.toJson());
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            List<Recipe> recipeList = reader.read();
            assertEquals(1, recipeList.size());
            checkRecipe("Hot chocolate", dirs, "5 mins", ings, "microwaveable", recipeList.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}