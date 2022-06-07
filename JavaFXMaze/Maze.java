package javafxmaze;

import java.util.Random;
/////////////////////////////////////////////
// 
//  I used this class for making the maze.
//
/////////////////////////////////////////////
public class Maze {
        private static Random rnd;
        //I only chnaged this to public
        //so I could read the values out of here.
	public char[][] maze;  // 2D array to represent maze

	public static int WIDTH = 25;
	public static int HEIGHT = 25;
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
        //Commented this out.
	// Display the maze in ASCII
//	public  void printMaze()
//	{
//	  for (int y=0; y < HEIGHT;y++)
//	  {
//		for (int x=0; x < WIDTH; x++)
//		{
//			System.out.print(maze[y][x]);
//		}
//		System.out.println();
//	  }
//	}

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
}