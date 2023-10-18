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
        String nameDescription = "Name Hot Chocolate\n";
        String idDescription = "Id 1\n";
        String nameAndDescription = nameDescription + idDescription;
        String timeDescription = "This recipe takes 45 minutes\n";
        String categoryDescription = "The category this recipe belongs to is baking\n";
        String ingredientsDescription = "The ingredients are: Cocoa powder Milk Vanilla Essence\n";
        String directionsDescription = "The steps are: Boil Milk\nAdd Cocoa Powder\nStir the mixture\n";
        String ingredientsAndDirections = ingredientsDescription + directionsDescription;
        String expectedOutput = nameAndDescription + timeDescription + categoryDescription + ingredientsAndDirections;
        assertEquals(expectedOutput, recipe.getInfo());
    }

    @Test
    void testGetDifficultyEasy() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Cocoa powder");
        ingredients.add("Milk");
        ingredients.add("Vanilla Essence");
        List<String> directions = new ArrayList<>();
        directions.add("Boil Milk");
        directions.add("Add Cocoa Powder");
        directions.add("Stir the mixture");
        recipe = new Recipe("Hot Chocolate",directions, "45 minutes", ingredients, "baking");
        assertEquals("EASY", recipe.getDifficultyLevel());
    }

    @Test
    void testGetDifficultyDifficult() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Cocoa powder");
        ingredients.add("Milk");
        ingredients.add("Vanilla Essence");
        ingredients.add("Eggs");
        List<String> directions = new ArrayList<>();
        directions.add("Add milk to a bowl");
        directions.add("Add cocoa powder");
        directions.add("Stir the mixture");
        directions.add("Add eggs");
        directions.add("Add heavy cream");
        directions.add("Stir the mixture");
        directions.add("Add vanilla essence");
        directions.add("Bake for 20 minutes");
        directions.add("Cool down for 15 minutes before serving");


        recipe = new Recipe("Chocolate Cake",directions, "60 minutes", ingredients, "baking");
        assertEquals("DIFFICULT", recipe.getDifficultyLevel());
    }

    @Test
    void testGetDifficultyIntermediate() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Eggs");
        ingredients.add("Milk");
        ingredients.add("Salt");
        ingredients.add("Pepper");
        ingredients.add("Butter");
        List<String> directions = new ArrayList<>();
        directions.add("Heat the butter");
        directions.add("Add eggs");
        directions.add("Scramble the eggs");
        directions.add("Add milk");
        directions.add("Add salt and pepper");
        directions.add("Let it simmer for 5 minutes before serving");


        recipe = new Recipe("Scramble Eggs",directions, "10 minutes", ingredients, "cooking");
        assertEquals("INTERMEDIATE", recipe.getDifficultyLevel());
    }


}