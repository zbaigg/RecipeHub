package model;


import java.util.ArrayList;
import java.util.List;

//This class stores a list of multiple recipes
public class RecipeList {
    private List<Recipe> recipes;

    public RecipeList() {

        this.recipes = new ArrayList<>();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    // EFFECTS: Adds multiple recipes to the list of recipes
    public void addRecipes(List<Recipe> newRecipes) {
        this.recipes.addAll(newRecipes);
    }

    // EFFECTS: Adds a single recipe to the list of recipes
    public void addRecipe(String name, List<String> directions, String time, List<String> ingList, String category) {
        Recipe newRecipe = new Recipe(name, directions, time, ingList, category);
        this.recipes.add(newRecipe);
        EventLog.getInstance().logEvent(new Event("Added a new recipe to the list of recipes"));

    }

    //EFFECTS: Removes a recipe from the list of recipes
    public void remove(Recipe recipe) {
        this.recipes.remove(recipe);
    }

    //EFFECTS: Obtains easy recipes by difficulty
    public List<Recipe> getEasyRecipes() {
        List<Recipe> easyRecipes = new ArrayList<>();
        for (Recipe recipeObj: this.recipes) {
            if (recipeObj.getDifficultyLevel().equals("EASY")) {
                easyRecipes.add(recipeObj);
            }
        }

        EventLog.getInstance().logEvent(new Event("Found easy recipes from the list of recipes"));
        return easyRecipes;
    }

}
