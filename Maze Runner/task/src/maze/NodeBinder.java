package maze;

public class NodeBinder {

    public static MazeNode[][] bindNodes(int rows, int cols) {

        MazeNode[][] mazeNodes = new MazeNode[rows][cols];
        int counter = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {


                MazeNode currentNode = new MazeNode();
                mazeNodes[i][j] = currentNode;
                currentNode.setNumber(++counter);


            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {


                MazeNode currentNode = mazeNodes[i][j];


                try {
                    currentNode.setDown(mazeNodes[i + 1][j]);
                } catch (Exception e) {
                    // System.out.println("No down Node");
                }
                try {
                    currentNode.setUp(mazeNodes[i - 1][j]);
                } catch (Exception e) {
                    //  System.out.println("No up Node");
                }
                try {
                    currentNode.setLeft(mazeNodes[i][j - 1]);
                } catch (Exception e) {
                    //  System.out.println("No left Node");
                }
                try {
                    currentNode.setRight(mazeNodes[i][j + 1]);
                } catch (Exception e) {
                    // System.out.println("No right Node");
                }
            }
        }
        return mazeNodes;
    }
}