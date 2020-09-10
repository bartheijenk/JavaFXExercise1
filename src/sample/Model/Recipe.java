package sample.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class Recipe {
    private final StringProperty title = new SimpleStringProperty();
    private String directions;
    private int servings;
    private ArrayList<Ingredient> ingredients;
    private int id;

    public Recipe(int id, String title, String directions, int servings) {
        ingredients = new ArrayList<>();
        setTitle(title);
        setDirections(directions);
        setServings(servings);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    @Override
    public String toString() {
        return  titleProperty().get();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void addIngredient(Ingredient i) {
        ingredients.add(i);
    }

    public void addAllIngredients(Ingredient[] ingredients) {
        this.ingredients.addAll(Arrays.asList(ingredients));
    }

    public String getIngredients() {
        String output = "";
        for (Ingredient i :
                this.ingredients) {
            output += i.toString() + "\n";
        }
        return output;
    }

    public Object[] getIngredientsList() {

        return this.ingredients.toArray();

    }
}
