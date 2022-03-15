package maze;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the size of a maze\n>");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        MazeNode[][] mazeNodes = NodeBinder.bindNodes(rows, cols);

        MazePrinter mazePrinter = new MazePrinter(mazeNodes,rows,cols);
        mazePrinter.printMaze();
    }
}