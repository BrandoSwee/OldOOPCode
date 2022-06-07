////////////////////////////////////////////////
//
//  Brandon Sweeney
//  Assignment 3 
//  Maze Displayer 
//  10 - 30 -19
//
////////////////////////////////////////////////
package javafxmaze;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program displays a maze from the Maze class.
//
// INPUTS: One Maze objects to create a javafx maze.
// 
// OUTPUTS:  The program displays a maze of size (300, 300).
//      It creates black rectangles on a white canvas to
//      show the walls and paths of the maze.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class JavaFXMaze extends Application {
    @Override
    public void start(Stage primaryStage) {
        Maze maze = new Maze();
        maze.digTunnel(1,1);
        //Wasn't sure if a GridPane was wanted
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(300, 300);
        //12*25 = 300
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        for(int i = 0;i < Maze.HEIGHT;i++){
            for(int j = 0; j < Maze.WIDTH;j++){
                if(maze.maze[i][j] == 'X'){
                    gc.fillRect(i*12, j*12, 12, 12);
                }
            }
        }
        
        root.getChildren().add(canvas);
        primaryStage.setTitle("Maze");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
                launch(args);
    }
}