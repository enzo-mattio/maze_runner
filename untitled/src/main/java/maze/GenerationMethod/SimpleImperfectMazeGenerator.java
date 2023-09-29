package maze.GenerationMethod;
import maze.MazeGenerator;
import maze.Cell;

import java.util.*;

public class SimpleImperfectMazeGenerator implements MazeGenerator {
    private final Random random = new Random();

    // Constructeur du générateur de labyrinthes parfaits simples
    public SimpleImperfectMazeGenerator(int width, int height, Cell[] maze) {
        generate(maze, width, height);
    }

    // Méthode principale de génération du labyrinthe
    @Override
    public void generate(Cell[] maze, int width, int height) {
        Stack<Integer> stack = new Stack<>();
        int totalCells = width * height;
        int currentCell = 0;
        int visitedCells = 1;

        while (visitedCells < totalCells) {
            maze[currentCell].setVisited();
            List<Integer> neighbors = getUnvisitedNeighbors(currentCell, width, height, maze);

            if (!neighbors.isEmpty()) {
                int randomNeighbor = neighbors.get(random.nextInt(neighbors.size()));
                removeWalls(currentCell, randomNeighbor, maze, width);
                stack.push(currentCell);
                currentCell = randomNeighbor;
                visitedCells++;
            } else if (!stack.isEmpty()) {
                currentCell = stack.pop();
            }
        }
        if (maze[0].hasWall(0)) {
            maze[0].removeWall(1);
            maze[1].removeWall(3);
        } else {
            maze[0].removeWall(2);
            maze[width].removeWall(0);
        }
    }

    // Méthode pour obtenir les voisins non visités d'une cellule donnée
    private List<Integer> getUnvisitedNeighbors(int cell, int width, int height, Cell[] maze) {
        List<Integer> neighbors = new ArrayList<>();
        int x = cell % width;
        int y = cell / width;

        if (x > 0 && maze[cell - 1].isVisited()) {
            neighbors.add(cell - 1); // Voisin à gauche
        }
        if (x < width - 1 && maze[cell + 1].isVisited()) {
            neighbors.add(cell + 1); // Voisin à droite
        }
        if (y > 0 && maze[cell - width].isVisited()) {
            neighbors.add(cell - width); // Voisin du haut
        }
        if (y < height - 1 && maze[cell + width].isVisited()) {
            neighbors.add(cell + width); // Voisin du bas
        }

        return neighbors;
    }

    // Méthode pour supprimer les murs entre deux cellules voisines
    private void removeWalls(int currentCell, int neighborCell, Cell[] maze, int width) {
        int diff = currentCell - neighborCell;

        if (diff == 1) {
            maze[currentCell].removeWall(3); // Supprimer le mur à gauche de la cellule courante
            maze[neighborCell].removeWall(1); // Supprimer le mur à droite du voisin
        } else if (diff == -1) {
            maze[currentCell].removeWall(1); // Supprimer le mur à droite de la cellule courante
            maze[neighborCell].removeWall(3); // Supprimer le mur à gauche du voisin
        } else if (diff == width) {
            maze[currentCell].removeWall(0); // Supprimer le mur en haut de la cellule courante
            maze[neighborCell].removeWall(2); // Supprimer le mur en bas du voisin
        } else if (diff == -width) {
            maze[currentCell].removeWall(2); // Supprimer le mur en bas de la cellule courante
            maze[neighborCell].removeWall(0); // Supprimer le mur en haut du voisin
        }
    }
}
