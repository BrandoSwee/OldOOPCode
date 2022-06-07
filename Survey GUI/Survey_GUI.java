////////////////////////////////////////////////
//
//  Brandon Sweeney
//  Assignment 3 
//  Survey GUI
//  10 - 30 -19
//
////////////////////////////////////////////////
package survey_gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program gives a survey.
//
// INPUTS: None
// 
// OUTPUTS:  The program displays the survey.
//      When you click finish the program will print
//      you results based on your answers.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
public class Survey_GUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(3.0);
        root.getChildren().add(new Label("Welcome to a short survey"));
        //Question 1
        root.getChildren().add(new Label("What is your favorite color?"));
        ToggleGroup toggleColor = new ToggleGroup();
        RadioButton rbRed = new RadioButton("Red");
        rbRed.setToggleGroup(toggleColor);
        rbRed.setSelected(true);
        RadioButton rbOrange = new RadioButton("Orange");
        rbOrange.setToggleGroup(toggleColor);
        RadioButton rbBlue = new RadioButton("Blue");
        rbBlue.setToggleGroup(toggleColor);
        RadioButton rbGreen = new RadioButton("Green");
        rbGreen.setToggleGroup(toggleColor);
        root.getChildren().add(rbRed);
        root.getChildren().add(rbOrange);
        root.getChildren().add(rbBlue);
        root.getChildren().add(rbGreen);
        //Question 2
	root.getChildren().add(new Label("Select your age?"));
        Spinner<Integer> spinnerAge = new Spinner<>();
        SpinnerValueFactory<Integer> ageFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory
                (10, 100, 10);
        spinnerAge.setValueFactory(ageFactory);
        root.getChildren().add(spinnerAge);
        //Question 3
	root.getChildren().add(new Label("Select your favorite programming "
                + "language."));
	ChoiceBox<String> cbLanguages = new ChoiceBox<>(
        FXCollections.observableArrayList("Java", "C++", "Python", "C#"));
        root.getChildren().add(cbLanguages);
        
	Button btnSelections = new Button("Finish");
	btnSelections.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String word;
                    if(rbRed.isSelected()){
                        word = "Red";
                    }
                    else if(rbBlue.isSelected()){
                        word = "Blue";
                    }
                    else if(rbOrange.isSelected()){
                        word = "Orange";
                    }
                    else{
                        word = "Green";
                    }
                    System.out.println("Your favorite color is " + word + ".");
                    System.out.println("You are " + spinnerAge.getValue() +
                            " years old.");
                    System.out.println("And your favorite programming language is "
                            + cbLanguages.getValue() + ".");
                }
        }
	);
	root.getChildren().add(btnSelections);
        
        Scene scene = new Scene(root, 350, 280);
        primaryStage.setTitle("Survey");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
