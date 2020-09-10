package sample;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.Model.Ingredient;
import sample.Model.Recipe;
import sample.data.RecipeJSONParser;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML ComboBox<Recipe> comboBox;
    @FXML public Label servings;
    @FXML public Text directions;
    @FXML public TextFlow directionsFlow;
    @FXML public Button widen;
    @FXML public GridPane gridPane;
    @FXML public Text ingredientListView;
    @FXML Button edit;

    ArrayList<Recipe> recipes;
    private boolean wide = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recipes = RecipeJSONParser.getRecipes();

        comboBox.getItems().setAll(FXCollections.observableArrayList(recipes));

        comboBox.setConverter(new StringConverter<Recipe>() {
            @Override
            public String toString(Recipe o) {
                return o.getTitle();
            }

            @Override
            public Recipe fromString(String s) {
                return null;
            }
        });

        servings.textProperty().bind(Bindings.selectString(comboBox.getSelectionModel().selectedItemProperty(), "servings"));
        directions.textProperty().bind(Bindings.selectString(comboBox.getSelectionModel().selectedItemProperty(), "directions"));
        ingredientListView.textProperty().bind(Bindings.selectString(comboBox.getSelectionModel().selectedItemProperty(), "ingredients"));

    }

    public void widenButton(ActionEvent e) {
        if(!wide) {
            gridPane.setPrefWidth(500);
            widen.setText("Unwiden");
            wide = true;
        }
        else {
            gridPane.setPrefWidth(200);
            widen.setText("Widen");
            wide = false;
        }
    }

    public void goToEditPage(ActionEvent e) {
        try {
            FXMLLoader ldr = new FXMLLoader();
            ldr.setLocation(new URL("file:src/sample/editPage.fxml"));
            Scene scene = ldr.load();
            Controller2 c = ldr.getController();

            Recipe selectedRecipe = comboBox.getValue();
            c.setRecipe(selectedRecipe);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Simple form Exampel 2");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
