////////////////////////////////////////////////
//
//  Brandon Sweeney
//  Assignment 3 
//  Gerrymandering
//  10 - 31 -19
//
////////////////////////////////////////////////
package gerrymandering;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.FileChooser.ExtensionFilter;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program displays information based on the
//          votes per district excel data.
//
// INPUTS: Excel file for reading from
// 
// OUTPUTS: Hboxes with values based on excel file.
//      Wasted votes and percentages based on the results.
//      Text saying which party the vote in gerrymandered in
//      favor of (or not gerrymandered).
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Gerrymandering extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox selectionZone = new VBox();
        ScrollPane results = new ScrollPane();
        results.setPrefSize(10,10);
        VBox resultPrint = new VBox();
        resultPrint.setSpacing(5.0);
        Button load = new Button();
        load.setText("Choose a file");
        FileChooser m = new FileChooser();
        
        resultPrint.getChildren().add(new Label("Alaska Gerrymandering Results"));
        load.setOnAction(
                new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    //I don't know how to get to the exact file where the
                    //excel files are 
                    m.setInitialDirectory(new File(System.getProperty("user.home")));
                    m.getExtensionFilters().addAll(
                    new ExtensionFilter("Excel file" , "*.csv"));
                    File file = m.showOpenDialog(primaryStage);
                    if (file != null) {
                            try {
                                Scanner input = new Scanner(new FileInputStream(file));
                                input.useDelimiter(",|\r\n");
                                int totalDVotes = 0;
                                int totalRVotes = 0;
                                int wastedDVotes = 0;
                                int wastedRVotes = 0;
                                while (input.hasNext()){
                                    String s = input.next();
                                    int numD = input.nextInt(); 
                                    int numR = input.nextInt();
                                    totalDVotes += numD;
                                    totalRVotes += numR;
                                    HBox hbox = new HBox();
                                    int total = numD + numR;
                                    if(numD > numR){
                                        wastedRVotes += numR;
                                        wastedDVotes += numD -(int)Math.floor((double)total/2.0) - 1;
                                    }
                                    else if(numR > numD){
                                        wastedDVotes += numD;
                                        wastedRVotes += numR - (int)Math.floor((double)total/2.0) - 1;
                                    }
                                    //If they are equal?
//                                    else{
//                                        wastedDVotes += numD;
//                                        wastedRVotes += numR;
//                                    }
                                    double num1Percent =((double)numD/(double)total)*100.0;
                                    double num2Percent =((double)numR/(double)total)*100.0;
                                    hbox.setMaxWidth(550);
                                    Rectangle rect1 = new Rectangle(num1Percent*4.0,25);
                                    Rectangle rect2 = new Rectangle(num2Percent*4.0,25);
                                    rect1.setFill(Color.BLUE);
                                    rect2.setFill(Color.RED);
                                    Label name = new Label();
                                    name.setText(s);
                                    hbox.getChildren().add(rect1);
                                    hbox.getChildren().add(rect2);
                                    hbox.getChildren().add(name);
                                    resultPrint.getChildren().add(hbox);                                      
                                }
                                input.close();
                                //I don't understand why my numbers are slightly
                                //different. 
                                //Compared to the given example.
                                double demWastedPercent =((double)wastedDVotes/(double)totalDVotes)*100;
                                double repWastedPercent =((double)wastedRVotes/(double)totalRVotes)*100;
                                selectionZone.getChildren().add(new Label("Wasted Democratic Votes: "
                                    + wastedDVotes + ". " + demWastedPercent + "%"));
                                selectionZone.getChildren().add(new Label("Wasted Republican Votes: "
                                    + wastedRVotes + ". " + repWastedPercent + "%"));
                                System.out.print(totalDVotes + " " + totalRVotes);
                                if(demWastedPercent > (repWastedPercent + 7)){
                                    selectionZone.getChildren().add(new Label("Gerrymandered in favor of"
                                            + " the republican party."));
                                }
                                else if(repWastedPercent > (demWastedPercent + 7)){
                                    selectionZone.getChildren().add(new Label("Gerrymandered in favor of"
                                            + " the democratic party."));
                                }
                                else{
                                    selectionZone.getChildren().add(new Label("Not gerrymandered"));
                                }
                            } catch (Exception ex) {
                                    System.out.println(ex);
                                }
                            }
                }
        });
        selectionZone.getChildren().add(load);
        results.setContent(resultPrint); 
        root.setLeft(selectionZone);    
        root.setCenter(results);
           
        Scene scene = new Scene(root, 850, 600);        
        primaryStage.setTitle("Gerrymandering");
        primaryStage.setScene(scene);
        primaryStage.show();
}
    public static void main(String[] args) {
        launch(args);
    }
}