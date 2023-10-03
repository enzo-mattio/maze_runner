package maze.GenerationMethod;
import maze.Cell;
import maze.MazeGenerator;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class SimpleImperfectMazeGenerator implements MazeGenerator {

    private final Random random = new Random();

    public SimpleImperfectMazeGenerator(Cell[] maze, int width, int height) {
        generate(maze, width, height);
    }

    @Override
    public void generate(Cell[] maze, int width, int height) {

        Stack<Integer> path = new Stack<>(); // Path of visited cells
        path.push(0); // Starting cell

        while (!path.isEmpty()) {
            int id = path.peek(); // Current cell
            maze[id].setVisited(); // Marks current cell as visited
            List<Integer> neighbors = getUnvisitedNeighbors(id, width, height, maze); // List of unvisited neighbors

            if (!neighbors.isEmpty()) {
                int nextCell = neighbors.get(random.nextInt(neighbors.size())); // Chooses a random neighbor
                int wallToRemove = getWallToRemove(id, nextCell, width); // Gets the wall to remove
                maze[id].removeWall(wallToRemove);
                maze[nextCell].setVisited();
                int oppositeWall = (wallToRemove + 2) % 4;
                maze[nextCell].removeWall(oppositeWall);
                path.push(nextCell); // Makes neighbor current for next iteration
            } else {
                path.pop(); // Backtracks if no unvisited neighbors
            }
        }
        if (maze[0].hasWall(1)) {
            maze[0].removeWall(1);
            maze[1].removeWall(3);
        } else {
            maze[0].removeWall(2);
            maze[width].removeWall(0);
        }
    }

    private List<Integer> getUnvisitedNeighbors(int id, int width, int height, Cell[] cell) {
        List<Integer> neighbors = new ArrayList<>();
        int x = id % width;
        int y = id / width;
        if (y > 0 && !cell[id - width].isVisited()) {
            neighbors.add(id - width);
        }
        if (x < width - 1 && !cell[id + 1].isVisited()) {
            neighbors.add(id + 1);
        }
        if (y < height - 1 && !cell[id + width].isVisited()) {
            neighbors.add(id + width);
        }
        if (x > 0 && !cell[id - 1].isVisited()) {
            neighbors.add(id - 1);
        }
        return neighbors; // Returns list of unvisited neighbors
    }

    private int getWallToRemove(int id, int nextCell, int width) { // Targets the direction of the next cell from the current cell
        if (nextCell == id - width) {
            return 0;
        } else if (nextCell == id + 1) {
            return 1;
        } else if (nextCell == id + width) {
            return 2;
        } else {
            return 3;
        }
    }
}