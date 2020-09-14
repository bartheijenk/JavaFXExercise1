package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Model.Ingredient;
import sample.Model.Recipe;
import sample.data.RecipeJSONParser;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2{

    @FXML public TextArea directions;
    @FXML public TextField title;
    @FXML public ListView<Ingredient> ingredientListView;
    @FXML public Spinner servings;
    @FXML public Button save;


    private Recipe recipe;


    public void saveRecipe(ActionEvent e) {
        recipe.setTitle(title.getText());
        recipe.setDirections(directions.getText());
        //recipe.setServings((Integer) servings.getValue());
        RecipeJSONParser.saveRecipe(recipe);
    }

    public void setRecipe(Recipe selectedRecipe) {
        this.recipe = selectedRecipe;

        title.setText(selectedRecipe.getTitle());
        directions.setText(selectedRecipe.getDirections());
        servings = new Spinner(0,10000000, selectedRecipe.getServings());

        Object[] ingredients = selectedRecipe.getIngredientsList();

        for (Object i :
                ingredients) {
            ingredientListView.getItems().add((Ingredient) i);
        }
    }
}
