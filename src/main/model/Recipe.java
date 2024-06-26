package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.Random;

//This class stores information for a recipe such as name, ingredients, directions etc.
//Exposes functionality to get the difficulty level of a recipe,
//and checks if this recipe can be made with the available ingredients
public class Recipe implements Writable {
    private final int recipeId;
    private final String name;
    private final List<String> directions;
    private final String time;
    private final List<String> ingredients;
    private final String category;

    public Recipe(String name, List<String> directions, String time, List<String> ingredients, String category) {
        int asciiSum = 0;
        char[] characters = name.toCharArray();
        for (char c: characters) {
            asciiSum = asciiSum + (int)c;
        }
        this.recipeId = asciiSum;
        this.name = name;
        this.directions = directions;
        this.time = time;
        this.ingredients = ingredients;
        this.category = category;
    }

    // EFFECTS: Returns recipe name and ID
    public String getInfo() {
        String nameDesc = "Name " + this.getName() + "\n";
        String idDesc = "Id " + this.getRecipeId() + "\n";
        String nameAndId = nameDesc + idDesc;
        String timeDesc = "This recipe takes " + this.getTime() + "\n";
        String categoryDesc = "The category this recipe belongs to is " + this.getCategory() + "\n";
        StringBuilder ingredientsDescription = new StringBuilder();
        ingredientsDescription.append("The ingredients are: ");
        for (String ingredient : this.getIngredients()) {
            ingredientsDescription.append(ingredient);
            ingredientsDescription.append(" ");
        }

        StringBuilder directionsDescription = new StringBuilder();
        directionsDescription.append("\n" + "The steps are: ");
        for (String step : this.getDirections()) {
            directionsDescription.append(step);
            directionsDescription.append("\n");
        }
        return nameAndId + timeDesc + categoryDesc + ingredientsDescription + directionsDescription;
    }

    // EFFECTS: Checks and returns boolean to show if the recipe is possible to make with available ingredients
    public boolean canMakeRecipeWithGivenIngredients(List<String> givenIngredients) {
        return givenIngredients.containsAll(this.ingredients);
    }

    //EFFECTS: Checks the size of the directions and returns the corresponding difficulty level
    public String getDifficultyLevel() {
        if (this.directions.size() > 8) {
            return "DIFFICULT";
        } else if (this.directions.size() < 4) {
            return "EASY";
        }
        return "INTERMEDIATE";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipeId", recipeId);
        json.put("name", name);
        json.put("directions", directions);
        json.put("time", time);
        json.put("ingredients", ingredients);
        json.put("category", category);
        return json;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Recipe)) {
            return false;
        }

        Recipe recipe = (Recipe) o;

        return getRecipeId() == recipe.getRecipeId();
    }
}
