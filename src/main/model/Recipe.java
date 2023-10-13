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
        String nameDescription = "Name " + this.getName() + "\n";
        String timeDescription = "This recipe takes " + this.getTime() + "\n";
        String categoryDescription = "The category this recipe belongs to is " + this.getCategory() + "\n";
        StringBuilder ingredientsDescription = new StringBuilder();
        ingredientsDescription.append("The ingredients are: ");
        for (String ingredient : this.ingredients) {
            ingredientsDescription.append(ingredient);
            ingredientsDescription.append(" ");
        }

        StringBuilder directionsDescription = new StringBuilder();
        directionsDescription.append("The steps are: ");
        for (String step : this.directions) {
            directionsDescription.append(step);
            directionsDescription.append("\n");
        }
        return nameDescription + timeDescription + categoryDescription + ingredientsDescription + directionsDescription;
    }

    // EFFECTS: Checks and returns boolean to show if the recipe is possible to make with available ingredients
    public boolean canMakeRecipeWithGivenIngredients(List<String> givenIngredients) {
        return givenIngredients.containsAll(this.ingredients);
    }

    public String getDifficultyLevel() {
        if (this.directions.size() > 8) {
            return "DIFFICULT";
        } else if (this.directions.size() < 4) {
            return "EASY";
        }

        return "INTERMEDIATE";
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
