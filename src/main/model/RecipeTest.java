package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void runBefore() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Cocoa powder");
        ingredients.add("Milk");
        ingredients.add("Vanilla Essence");
        List<String> directions = new ArrayList<>();
        directions.add("Boil Milk");
        directions.add("Add Cocoa Powder");
        directions.add("Stir the mixture");
        recipe = new Recipe("Hot Chocolate",directions, "45 minutes", ingredients, "baking");
    }

    @Test
    void testConstructor() {
        assertEquals("Hot Chocolate", recipe.getName());
    }

    @Test
    void testCanMakeRecipeWithGivenIngredients() {
        List<String> availableIngredients = new ArrayList<>();
        availableIngredients.add("Cocoa powder");
        availableIngredients.add("Milk");
        availableIngredients.add("Vanilla Essence");
        availableIngredients.add("Another not needed ingredient");

        assertTrue(recipe.canMakeRecipeWithGivenIngredients(availableIngredients));
    }

    @Test
    void testCanMakeRecipeWithGivenIngredientsIncompleteIngredients() {
        List<String> availableIngredients = new ArrayList<>();
        availableIngredients.add("Cocoa powder");
        availableIngredients.add("Milk");
        availableIngredients.add("Another not needed ingredient");

        assertFalse(recipe.canMakeRecipeWithGivenIngredients(availableIngredients));
    }

    @Test
    void testGetInfo() {
        String info = "Name: " + "Hot Chocolate" + "\t\t\t" + "Id : " + "1" + "\n";

        assertEquals(info, recipe.getInfo());
    }

}