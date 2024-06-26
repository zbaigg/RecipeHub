package ui;

import model.Recipe;
import model.RecipeList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//This class represents the CookBook of all the recipes,
//exposes functionality for user to add, delete, and view recipes. It also
//allows user to check the difficulty of the recipe and
//allows users to view all the recipes that they can make with the ingredients they have
public class CookBookApp implements Writable {
    private static final String JSON_STORE = "./data/recipes.json";

    private RecipeList recipeList;

    private final Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public CookBookApp(Boolean isTestingMode) {
        this.recipeList = new RecipeList();

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);

        if (!isTestingMode) {
            runCookBookApp();
        }
    }

    public List<Recipe> getRecipes() {
        return this.recipeList.getRecipes();
    }

    // EFFECTS: allows user to interact with the application
    private void runCookBookApp() {
        boolean keepGoing = true;
        String command;
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays the user input menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> show all recipes");
        System.out.println("\ta -> add recipe");
        System.out.println("\td -> delete a recipe");
        System.out.println("\tc -> choose a particular recipe by id");
        System.out.println("\tn -> choose a particular recipe by name");
        System.out.println("\tp -> Get a list of the recipes based on the list of ingredients you have");
        System.out.println("\tf -> save recipes to file");
        System.out.println("\tl -> load recipes from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes the user input and calls the relevant methods
    private void processCommand(String command) {
        if (command.equals("s")) {
            displayRecipes();
        } else if (command.equals("c")) {
            System.out.println("\tEnter recipe id:");
            command = input.next();
            Recipe recipe = this.getRecipeById(Integer.parseInt(command));
            this.printRecipe(recipe);
        } else if (command.equals("a")) {
            this.addRecipeUi();
        } else if (command.equals("d")) {
            this.deleteRecipe();
        } else if (command.equals("n")) {
            System.out.println("\tEnter recipe name:");
            command = input.next().toLowerCase();
            Recipe recipe = this.getRecipeByName(command);
            this.printRecipe(recipe);
        } else if (command.equals("p")) {
            this.getRecipesWithGivenIngredients();
        } else if (command.equals("f") || command.equals("l")) {
            this.handleSaveAndLoadCommands(command);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void handleSaveAndLoadCommands(String command) {
        if (command.equals("f")) {
            saveRecipeList();
        } else if (command.equals("l")) {
            loadRecipeList();

        }
    }

    // EFFECTS: saves the recipes to file
    public void saveRecipeList() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.toJson());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadRecipeList() {
        try {
            this.recipeList.addRecipes(jsonReader.read());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Prompts user to enter the list of ingredients
    // they have and then the system prints all the recipes that the user can make
    public void getRecipesWithGivenIngredients() {
        System.out.println("Enter the ingredients you have\n");
        List<String> availableIngredients = this.getCommaSeparatedInput();
        List<Recipe> possibleRecipes = new ArrayList<>();

        for (Recipe recipe: this.recipeList.getRecipes()) {
            if (recipe.canMakeRecipeWithGivenIngredients(availableIngredients)) {
                possibleRecipes.add(recipe);
            }
        }
        System.out.println("You have all the required ingredients for the following recipes:\n");
        for (Recipe recipe: possibleRecipes) {
            System.out.println(recipe.getInfo());
        }

    }

    // MODIFIES: recipes cookbook
    // EFFECTS: Prompts user to add a new recipe
    public void addRecipeUi() {
        System.out.println("\tEnter Recipe name:");
        String name = this.getInput();

        System.out.println("\tEnter Recipe category:");
        String category = this.getInput();

        System.out.println("\tEnter Recipe cooking time:");
        String recipeTime = this.getInput();

        System.out.println("\tEnter Recipe ingredients separated by a comma:");
        List<String> ingredients = this.getCommaSeparatedInput();

        System.out.println("\tEnter Recipe steps separated by a comma:");
        List<String> recipeSteps = this.getCommaSeparatedInput();
        addRecipe(name, recipeSteps, recipeTime, ingredients, category);
    }

    // EFFECTS: Scans user input
    private String getInput() {
        String command;
        command = input.next();
        command = command.toLowerCase().trim();
        return command;

    }

    // EFFECTS: Scans user input separated by comma and returns a list of comma separated data
    private List<String> getCommaSeparatedInput() {
        String command;
        command = input.next();
        command = command.toLowerCase().trim();
        String inputValue = command;
        String[] splitValues = inputValue.split(",");
        List<String> itemsList = Arrays.asList(splitValues);
        List<String> trimmedValues = new ArrayList<>();

        for (String item: itemsList) {
            String trimmedValue = item.trim();
            trimmedValues.add(trimmedValue);
        }
        return trimmedValues;
    }

    // EFFECTS: Helper function for addRecipeUi
    public void addRecipe(String name, List<String> directions, String time, List<String> ingList, String category) {
        this.recipeList.addRecipe(name, directions, time, ingList, category);
    }

    public List<Recipe> getEasyRecipes() {
        return this.recipeList.getEasyRecipes();
    }

    // EFFECTS: Fetches the recipe by the recipe name
    public Recipe getRecipeByName(String name) {
        for (Recipe recipeObj: this.recipeList.getRecipes()) {
            if (recipeObj.getName().equals(name)) {
                return recipeObj;
            }
        }

        return null;
    }

    // EFFECTS: Fetches the recipe by the recipe ID
    public Recipe getRecipeById(int recipeId) {
        for (Recipe recipeObj: this.recipeList.getRecipes()) {
            if (recipeObj.getRecipeId() == recipeId) {
                return recipeObj;
            }
        }

        return null;
    }

    // EFFECTS: Fetches the recipe by the recipe name
    public void displayRecipes() {
        System.out.println("RECIPES ARE WRITTEN BELOW\n");

        for (Recipe eachRecipe: this.recipeList.getRecipes()) {
            System.out.println(eachRecipe.getInfo());
        }

    }

    // EFFECTS: Prints the recipe information
    public void printRecipe(Recipe recipe) {
        if (recipe == null) {
            System.out.println("The recipe name you entered is not valid");
            return;
        }
        System.out.println("Recipe Name: " + recipe.getName());
        System.out.println("Recipe category: " + recipe.getCategory());
        System.out.println("Recipe time: " + recipe.getTime());
        System.out.println("Ingredients required: ");
        for (String ingredient: recipe.getIngredients()) {
            System.out.println(ingredient);
        }
        System.out.println("Step by step instructions:");

        for (String step: recipe.getDirections()) {
            System.out.println(step);
        }
    }

    public void deleteRecipe() {
        System.out.println("Enter the id of the recipe you would like to delete: ");
        String recipeId = this.getInput();
        Recipe recipe = this.getRecipeById(Integer.parseInt(recipeId));
        this.recipeList.remove(recipe);
//        this.recipes.remove(recipe);

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "recipeList");
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: returns the recipes in the recipeList as a JSON array
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : this.recipeList.getRecipes()) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }


}
