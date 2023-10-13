package model;

import java.util.List;

public class Recipe {
    private static int recipeCount = 0;
    private final int recipeId;
    private final String name;
    private final List<String> directions;
    private final String time;
    private final List<String> ingredients;
    private final String category;

    public Recipe(String name, List<String> directions, String time, List<String> ingredients, String category) {
        this.recipeId = Recipe.recipeCount + 1;
        this.name = name;
        this.directions = directions;
        this.time = time;
        this.ingredients = ingredients;
        this.category = category;
        Recipe.recipeCount = Recipe.recipeCount + 1;
    }

    // EFFECTS: Returns recipe name and ID
    public String getInfo() {
        return "Name: " + this.getName() + "\t\t\t" + "Id : " + this.getRecipeId() + "\n";
    }

    // EFFECTS: Checks and returns boolean to show if the recipe is possible to make with available ingredients
    public boolean canMakeRecipeWithGivenIngredients(List<String> givenIngredients) {
        return givenIngredients.containsAll(this.ingredients);
    }


    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getCategory() {
        return category;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public List<String> getDirections() {
        return directions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
