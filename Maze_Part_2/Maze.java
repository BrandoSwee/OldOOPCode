////////////////////////////////////////////////
//
//  Brandon Sweeney
//  Assignment 4 
//  Maze Part 2
//  11 - 18 - 19
//
////////////////////////////////////////////////
package javamaze;
// Used the posted solution
// My original may have been wrong
import java.util.Random;
// Added imports for JavaFX
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

// Make the class extend Application to be a JavaFX app
public class Maze extends Application
{
	private static Random rnd;
	private char[][] maze;  // 2D array to represent maze
        private int playerX = 1, playerY = 1;  // Initial coordinates of player in maze
        private final int finishX = 23, finishY = 23; //coordinates for the finish
	public static int WIDTH = 25;
	public static int HEIGHT = 25;
	public static int CELLSIZE = 20; // Pixels in width/height for a cell to draw in the maze

	// Constructor
	public Maze()
	{
		rnd = new Random();
		maze = new char[HEIGHT][WIDTH];
		// Initialize the maze to walls everywhere
		for (int x = 0; x < WIDTH; x++)
		  for (int y = 0; y < HEIGHT; y++)
		  {
			maze[y][x]='X';
		  }
		maze[1][1]=' ';			// Start location
	}

	// Display the maze in ASCII
	public  void printMaze()
	{
	  for (int y=0; y < HEIGHT;y++)
	  {
		for (int x=0; x < WIDTH; x++)
		{
			System.out.print(maze[y][x]);
		}
		System.out.println();
	  }
	}

	// Display the maze on the Graphics Context
	public void drawMaze(GraphicsContext gc,int playerX,int playerY)
	{
	  	for (int y=0; y < HEIGHT;y++)
	  	{
			for (int x=0; x < WIDTH; x++)
			{
				if (maze[y][x]==' '){
					gc.setFill(Color.WHITE);    // Open space
                                }
                                else{
					gc.setFill(Color.GREEN);   // Wall
                                }
				gc.fillRect(x*CELLSIZE, y*CELLSIZE, CELLSIZE, CELLSIZE);
			}
	  }
                gc.setFill(Color.LIGHTSKYBLUE);
                gc.fillRect(playerX*CELLSIZE, playerY*CELLSIZE, CELLSIZE, CELLSIZE);
                gc.setFill(Color.YELLOW);
                gc.fillRect(finishX*CELLSIZE,finishY*CELLSIZE, CELLSIZE,CELLSIZE);
	}

	// This recursive method digs tunnels to create a maze
	public void digTunnel(int x, int y)
	{
		int dirs[] = {1,2,3,4};
		for (int i = 0; i < 4; i++)
		{
			int i1 = rnd.nextInt(4);
			int i2 = rnd.nextInt(4);
			int temp = dirs[i1];
			dirs[i1] = dirs[i2];
			dirs[i2] = temp;
		}

		for (int i = 0; i < 4; i++)
		{
			switch(dirs[i])
			{
				case 1:
					if ((y>2) && (maze[y-2][x]=='X'))
						{
							maze[y-1][x] = ' ';
							maze[y-2][x] = ' ';
							digTunnel(x,y-2);
						}
					break;
				case 2:
					if ((y<WIDTH-2) && (maze[y+2][x]=='X'))
						{
							maze[y+1][x] = ' ';
							maze[y+2][x] = ' ';
							digTunnel(x,y+2);
						}
					break;
				case 3:
					if ((x>2) && (maze[y][x-2]=='X'))
						{
							maze[y][x-1] = ' ';
							maze[y][x-2] = ' ';
							digTunnel(x-2,y);
						}
					break;
				case 4:
					if ((x<WIDTH-2) && (maze[y][x+2]=='X'))
						{
							maze[y][x+1] = ' ';
							maze[y][x+2] = ' ';
							digTunnel(x+2,y);
						}
			}
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program displays a maze from the Maze class.
//      It also lets the user move a light blue box around to the finish
//      at (23,23).
//      
// INPUTS: One Maze objects to create a javafx maze and
//      User inputs.
// 
// OUTPUTS:  The program displays a maze of size (500, 500).
//      It creates Green and white rectangles to
//      show the walls and paths of the maze.
//      The player and finish are yellow and light blue.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// What was the old main is now in the start method
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Maze maze = new Maze();
		maze.digTunnel(1,1);	// Dig tunnels to create the maze
 		maze.printMaze();

 		// Create the stage
		Group root = new Group();
                Scene scene = new Scene(root);

		Canvas canvas = new Canvas(WIDTH*CELLSIZE, HEIGHT*CELLSIZE);     // 30x30 box for each cell
		GraphicsContext gc = canvas.getGraphicsContext2D();

		maze.drawMaze(gc,playerX,playerY);	// Draw the maze on the graphics context
                canvas.setFocusTraversable(true);
                canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent k) {
                    if (k.getCode() == KeyCode.RIGHT) {
			System.out.println("RIGHT pressed: " + k.getCode());
                        if(maze.maze[playerY][playerX + 1] != 'X'){
                            playerX++;
                        }
                    }
                    else if(k.getCode() == KeyCode.LEFT){
                        System.out.println("LEFT pressed: " + k.getCode());
                        if(maze.maze[playerY][playerX - 1] != 'X'){
                            playerX--;
                        }
                    }
                    else if(k.getCode() == KeyCode.UP){
                        System.out.println("UP pressed: " + k.getCode());
                        if(maze.maze[playerY - 1][playerX] != 'X'){
                            playerY--;
                        }
                    }
                    else if(k.getCode() == KeyCode.DOWN){
                        System.out.println("DOWN pressed: " + k.getCode());
                        if(maze.maze[playerY + 1][playerX] != 'X'){
                            playerY++;
                        }
                    }
                    maze.drawMaze(gc,playerX,playerY);
                    if ((playerX == finishX) && (playerY == finishY)) {
                        System.out.println("You finished the maze");
                        System.exit(0);
                    }
                }
                });
		root.getChildren().add(canvas);
		primaryStage.setTitle("Maze");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// New main that just calls launch, generally not needed
	public static void main(String[] args)
	{
		launch(args);
	}
}