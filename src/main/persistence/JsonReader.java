//Source: This class is adapted from the JsonReader class in the JsonSerializationDemo file.

package persistence;

import model.Recipe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.List;

import org.json.*;

//This class represents a reader that reads recipes from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads recipes from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Recipe> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses recipes from JSON object and returns it
    private List<Recipe> parseRecipeList(JSONObject jsonObject) {
        return getRecipesList(jsonObject);
    }

    // EFFECTS: parses recipes from JSON object and returns them
    private List<Recipe> getRecipesList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        List<Recipe> savedRecipes = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            savedRecipes.add(getRecipe(nextRecipe));
        }
        return savedRecipes;
    }

    // EFFECTS: parses recipe from JSON object and returns it
    private Recipe getRecipe(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray directionsJson = jsonObject.getJSONArray("directions");
        List<String> directions = new ArrayList<>();
        for (int i = 0; i < directionsJson.length(); i++) {
            directions.add(directionsJson.getString(i));
        }
        String time = jsonObject.getString("time");
        JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsJson.length(); i++) {
            ingredients.add(ingredientsJson.getString(i));
        }
        String category = jsonObject.getString("category");
        return new Recipe(name, directions, time, ingredients, category);
    }
}
