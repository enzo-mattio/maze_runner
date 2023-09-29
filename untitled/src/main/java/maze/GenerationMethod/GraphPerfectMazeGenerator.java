package maze.GenerationMethod;

import maze.Cell;
import maze.DisjointSet;
import maze.Edge;
import maze.MazeGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GraphPerfectMazeGenerator implements MazeGenerator {
    private final Random random = new Random();

    public GraphPerfectMazeGenerator(int width, int height, Cell[] maze) {
        generate(maze, width, height);
    }

    @Override
    public void generate(Cell[] maze, int width, int height) {
        // Initialisation du graphe en tant qu'ensemble de cellules isolées
        DisjointSet disjointSet = new DisjointSet(width * height);

        // Création d'une liste d'arêtes
        List<Edge> edges = new ArrayList<>();

        // Ajoutez toutes les arêtes possibles entre les cellules voisines
        for (int i = 0; i < width * height; i++) {
            int x = i % width;
            int y = i / width;

            if (x < width - 1) {
                edges.add(new Edge(i, i + 1));
            }

            if (y < height - 1) {
                edges.add(new Edge(i, i + width));
            }
        }

        // Mélangez aléatoirement les arêtes
        Collections.shuffle(edges);

        // Parcourez les arêtes
        for (Edge edge : edges) {
            int cellA = edge.getCellA();
            int cellB = edge.getCellB();

            // Si les cellules ne sont pas déjà dans le même ensemble disjoint
            if (disjointSet.find(cellA) != disjointSet.find(cellB)) {
                // Retirez le mur entre ces cellules
                removeWallsBetweenCells(cellA, cellB, maze, width);

                // Fusionnez les ensembles disjoint de ces cellules
                disjointSet.union(cellA, cellB);
            }
        }
    }

    private void removeWallsBetweenCells(int cellA, int cellB, Cell[] maze, int width) {
        // Determine the direction from cellA to cellB
        int diff = cellB - cellA;
        if (diff == 1) {
            maze[cellA].removeWall(1); // Right of cellA
            maze[cellB].removeWall(3); // Left of cellB
        } else if (diff == -1) {
            maze[cellA].removeWall(3); // Left of cellA
            maze[cellB].removeWall(1); // Right of cellB
        } else if (diff == width) {
            maze[cellA].removeWall(2); // Bottom of cellA
            maze[cellB].removeWall(0); // Top of cellB
        } else if (diff == -width) {
            maze[cellA].removeWall(0); // Top of cellA
            maze[cellB].removeWall(2); // Bottom of cellB
        }
    }
}
