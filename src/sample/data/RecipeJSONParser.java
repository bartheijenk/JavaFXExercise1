package sample.data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.Model.Ingredient;
import sample.Model.Recipe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeJSONParser {
    private static JSONParser jsonParser = new JSONParser();
    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public static ArrayList<Recipe> getRecipes() {
        ArrayList<Recipe> recipeArrayList = null;
        try (FileReader reader = new FileReader("src/sample/data/data.json")) {
            Object o = jsonParser.parse(reader);

            recipeArrayList = new ArrayList<>();

            JSONArray recipeList = (JSONArray) o;
            System.out.println(recipeList);

            ArrayList<Recipe> finalRecipeArrayList = recipeArrayList;
            recipeList.forEach(rec -> finalRecipeArrayList.add(parseRecipeObject((JSONObject) rec)));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RecipeJSONParser.recipes = recipeArrayList;
        return recipeArrayList;
    }

    private static Recipe parseRecipeObject(JSONObject recipe) {
        Recipe outputRecipe;
        JSONObject recipeObject = (JSONObject) recipe.get("recipe");

        int id = ((Long) recipeObject.get("id")).intValue();
        String title = (String) recipeObject.get("title");
        String directions = (String) recipeObject.get("directions");
        int servings = ((Long) recipeObject.get("servings")).intValue();
        outputRecipe = new Recipe(id, title, directions, servings);

        JSONArray ingredients = (JSONArray) recipeObject.get("ingredients");
        ingredients.forEach(ing -> outputRecipe.addIngredient(parseIngredientObject((JSONObject) ing)));

        return outputRecipe;
    }

    private static Ingredient parseIngredientObject(JSONObject ingredient) {
        Ingredient outputIngredient;
        JSONObject ingredientObject = (JSONObject) ingredient.get("ingredient");

        String name = (String) ingredientObject.get("name");
        String measurement = (String) ingredientObject.get("measurement");
        double amount = (double) ingredientObject.get("amount");

        outputIngredient = new Ingredient(name, measurement, amount);

        return outputIngredient;

    }

    public static void saveRecipe(Recipe recipe) {
        for (Recipe r :
                recipes) {
            if (r.getId() == recipe.getId()) {
                r.setServings(recipe.getServings());
                r.setDirections(recipe.getDirections());
                r.setTitle(recipe.getTitle());
            }
        }

        JSONArray recipeArray = new JSONArray();
        for (Recipe r :
                recipes) {
            JSONObject recipeDetails = new JSONObject();
            recipeDetails.put("id", r.getId());
            recipeDetails.put("title", r.getTitle());
            recipeDetails.put("servings", r.getServings());
            recipeDetails.put("directions", r.getDirections());

            JSONArray ingredientsArray = new JSONArray();

            for (Object i :
                    r.getIngredientsList()) {
                Ingredient ingredient = (Ingredient) i;
                JSONObject ingredientsDetail = new JSONObject();
                ingredientsDetail.put("name", ingredient.getName());
                ingredientsDetail.put("measurement", ingredient.getMeasurement());
                ingredientsDetail.put("amount", ingredient.getAmount());

                JSONObject ingredientObject = new JSONObject();
                ingredientObject.put("ingredient", ingredientsDetail);

                ingredientsArray.add(ingredientObject);
            }

            recipeDetails.put("ingredients", ingredientsArray);
            JSONObject recipeObject = new JSONObject();
            recipeObject.put("recipe", recipeDetails);
            recipeArray.add(recipeObject);
        }

        try(FileWriter file = new FileWriter("src/sample/data/data.json")) {
            file.write(recipeArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
