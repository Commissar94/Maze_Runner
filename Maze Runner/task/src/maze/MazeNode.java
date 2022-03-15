package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MazeNode {

    private int number;
    private boolean wall = true;
    private MazeNode up;
    private MazeNode down;
    private MazeNode left;
    private MazeNode right;
    private boolean visited = false;

    public MazeNode getUp() {
        return up;
    }

    public void setUp(MazeNode up) {
        this.up = up;
    }

    public MazeNode getDown() {
        return down;
    }

    public void setDown(MazeNode down) {
        this.down = down;
    }

    public MazeNode getLeft() {
        return left;
    }

    public void setLeft(MazeNode left) {
        this.left = left;
    }

    public MazeNode getRight() {
        return right;
    }

    public void setRight(MazeNode right) {
        this.right = right;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<MazeNode> availableNodesToGo() {


        List<MazeNode> availableNodes = new ArrayList<>();


        try {
            MazeNode rightNode = getRight().getRight();
            if (!rightNode.visited) {
                availableNodes.add(rightNode);
            }
        } catch (Exception e) {

        }


        try {
            MazeNode leftNode = getLeft().getLeft();
            if (!leftNode.visited) {
                availableNodes.add(leftNode);
            }
        } catch (Exception e) {

        }


        try {
            MazeNode upNode = getUp().getUp();
            if (!upNode.visited) {
                availableNodes.add(upNode);
            }
        } catch (Exception e) {

        }


        try {
            MazeNode downNode = getDown().getDown();
            if (!downNode.visited) {
                availableNodes.add(downNode);
            }
        } catch (Exception e) {

        }


        return availableNodes.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public String toString() {

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        try {
            up = getUp().getNumber();
        } catch (Exception e) {

        }
        try {
            down = getDown().getNumber();
        } catch (Exception e) {

        }
        try {
            left = getLeft().getNumber();
        } catch (Exception e) {

        }
        try {
            right = getRight().getNumber();
        } catch (Exception e) {

        }

        return "MazeNode{" +
                "number=" + number +
                ", up=" + up +
                ", down=" + down +
                ", left=" + left +
                ", right=" + right +
                ", wall=" + wall +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeNode mazeNode = (MazeNode) o;
        return number == mazeNode.number && wall == mazeNode.wall && visited == mazeNode.visited && Objects.equals(up, mazeNode.up) && Objects.equals(down, mazeNode.down) && Objects.equals(left, mazeNode.left) && Objects.equals(right, mazeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, wall, up, down, left, right, visited);
    }
}