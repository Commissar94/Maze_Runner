package maze;

import java.util.Arrays;
import java.util.Random;

public class MazePrinter {

    public MazePrinter(int x, int y) {
        this.maze = new int[x][y];
        Random random = new Random();
        this.enter = random.nextInt((x - 1 - 1)) + 1;
        this.exit = random.nextInt((x - 1 - 1)) + 1;
        printWalls();
    }

    private String passage = "  ";
    private String wall = "\u2588\u2588";
    private int[][] maze;
    private int enter;
    private int exit;


    public void printWalls() {
        //left wall
        for (int i = 0; i < maze[0].length; i++) {
            if (i != enter) {
                maze[i][0] = 1;
            }
        }
        //right wall
        for (int i = 0; i < maze[0].length; i++) {
            if (i != exit) {
                maze[i][maze[1].length - 1] = 1;
            }

        }
        //up wall
        Arrays.fill(maze[0], 1);
        //down wall
        Arrays.fill(maze[9], 1);
    }

    public void printMaze() {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze[1].length; j++) {
                System.out.print(maze[i][j] == 0 ? passage : wall);
            }
            System.out.println();
        }
    }
}
