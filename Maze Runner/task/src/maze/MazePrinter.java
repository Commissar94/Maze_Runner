package maze;

import java.util.List;
import java.util.Random;

public class MazePrinter {

    public MazePrinter(MazeNode[][] mazeNodes, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.mazeNodes = mazeNodes;
        this.enter = MyRandom.exitOrEnter(rows);
        this.exit = MyRandom.exitOrEnter(rows);
        this.allNodes = rows * cols;
        setWallsEverywhereExceptPathNodes();
        setEnterAndExit();
        safeEnterAndExit();
        setWallsForOddInputs();
        calculatePathNodes();
        setBorderWalls();
        printPath();
    }

    private final int rows; //высота лабиринта
    private final int cols; //длина лабиринта
    private final MazeNode[][] mazeNodes;
    private final int enter;
    private final int exit;
    private int pathNodes;
    private final int allNodes;
    private int emptyDestinations = 0;

    //TODO
    //благодаря методу availableNodesToGo мы знаем куда можно пойти
    //теперь нужно выбирать путь рандомно, и отмечать посещенные ячейки изменяя visited у ноды
    //путь по нодам складываем в стэк, по нему возвращаемся когда вариантов не будет

    private void printPath() {


        Random random = new Random();


        while (findVisited() + emptyDestinations < allNodes) {
              for (int i = 1; i < rows - 1; i += 2) {  //i+=2
               for (int j = 1; j < cols - 1; j += 2) {
                    MazeNode currentNode = mazeNodes[i][j];
                    List<MazeNode> nodesToGo = currentNode.availableNodesToGo();

                    int randomDestination;

                    if (!nodesToGo.isEmpty()) {
                        randomDestination = random.nextInt(nodesToGo.size());

                        if (currentNode.getUp().getUp() != null){
                            if (nodesToGo.get(randomDestination).getNumber() == currentNode.getUp().getUp().getNumber() && !currentNode.getUp().getUp().isVisited()) {
                                currentNode.setVisited(true);
                                currentNode.getUp().setWall(false);
                            }
                        }
                        if (currentNode.getRight().getRight() != null) {
                            if (nodesToGo.get(randomDestination).getNumber() == currentNode.getRight().getRight().getNumber() && !currentNode.getRight().getRight().isVisited()) {
                                currentNode.setVisited(true);
                                currentNode.getRight().setWall(false);
                            }
                        }

                        if (currentNode.getDown().getDown() != null) {
                            if (nodesToGo.get(randomDestination).getNumber() == currentNode.getDown().getDown().getNumber() && !currentNode.getDown().getDown().isVisited()) {
                                currentNode.setVisited(true);
                                currentNode.getDown().setWall(false);
                            }
                        }
                        if (currentNode.getLeft().getLeft() != null) {
                            if (nodesToGo.get(randomDestination).getNumber() == currentNode.getLeft().getLeft().getNumber() && !currentNode.getLeft().getLeft().isVisited()) {
                                currentNode.setVisited(true);
                                currentNode.getLeft().setWall(false);
                            }
                        }
                    } else {
                        this.emptyDestinations++;
                    }
                }
            }
        }

    }

    private void setWallsEverywhereExceptPathNodes() {
        for (int i = 1; i < rows; i += 2) {
            for (int j = 1; j < cols; j += 2) {
                if (!mazeNodes[i][j].isVisited()) {
                    mazeNodes[i][j].setWall(false);
                }
            }
        }

    }

    private void calculatePathNodes() {
        //this.pathNodes = ((this.x - 2) / 2 + 1) * ((this.y - 2) / 2 + 1);
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                this.pathNodes++;
            }
        }
        this.pathNodes = this.pathNodes / 2;
    }

    private void setBorderWalls() {
        //left wall
        for (int i = 0; i < mazeNodes.length; i++) {
            if (i != enter) {
                mazeNodes[i][0].setWall(true);
                mazeNodes[i][0].setVisited(true);
            }
        }
        //right wall
        for (int i = 0; i < mazeNodes.length; i++) {
            if (i != exit) {
                mazeNodes[i][mazeNodes[1].length - 1].setWall(true);
                mazeNodes[i][mazeNodes[1].length - 1].setVisited(true);
            }

        }
        //up wall
        for (int i = 0; i < mazeNodes[0].length; i++) {
            mazeNodes[0][i].setWall(true);
            mazeNodes[0][i].setVisited(true);
        }
        //down wall
        for (int i = 0; i < mazeNodes[rows - 1].length; i++) {
            mazeNodes[rows - 1][i].setWall(true);
            mazeNodes[rows - 1][i].setVisited(true);
        }
    }

    private void setEnterAndExit() {
        mazeNodes[enter][0].setWall(false);
        mazeNodes[enter][0].setVisited(true);
        mazeNodes[exit][mazeNodes[1].length - 1].setWall(false);
        mazeNodes[exit][mazeNodes[1].length - 1].setVisited(true);
    }

    private void setWallsForOddInputs() {
        if (this.cols % 2 == 0) {
            for (int i = 0; i < mazeNodes.length; i += 2) {
                mazeNodes[i][mazeNodes[1].length - 2].setWall(false);
            }
        }
        if (this.rows % 2 == 0) {
            for (int i = 0; i < mazeNodes[rows - 1].length; i += 2) {
                mazeNodes[rows - 2][i].setWall(false);
            }
        }
    }

    public void printMaze() {

        String passage = "  ";
        String wall = "\u2588\u2588";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(!mazeNodes[i][j].getWall() ? passage : wall);
            }
            System.out.println();
        }
    }

    private void safeEnterAndExit() {
        mazeNodes[enter][0].getRight().setWall(false);
        //mazeNodes[enter][0].getRight().setVisited(true);

        mazeNodes[exit][mazeNodes[1].length - 1].getLeft().setWall(false);
        //  mazeNodes[exit][mazeNodes[1].length - 1].getLeft().setVisited(true);
    }

    private int findVisited() {
        int visitedNodes = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mazeNodes[i][j].isVisited()) {
                    visitedNodes++;
                }
            }
        }
        return visitedNodes;
    }
}