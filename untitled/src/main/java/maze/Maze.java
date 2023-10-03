package maze;

import maze.GenerationMethod.GraphImperfectMazeGenerator;
import maze.GenerationMethod.GraphPerfectMazeGenerator;
import maze.GenerationMethod.SimpleImperfectMazeGenerator;
import maze.GenerationMethod.SimplePerfectMazeGenerator;
import maze.exception.MazeGenerationException;

public class Maze {
    private final int width;
    private final int height;
    public String generationType, generatorMethod;

    private Cell[] maze;

    public Maze(int width, int height, String generationType, String generatorMethod) throws MazeGenerationException {
        this.width = width;
        this.height = height;
        this.generationType = generationType;
        this.generatorMethod = generatorMethod;
        this.maze = new Cell[width * height];
        initMaze();
        SelectMaze();
        printMaze();
    }

    public void SelectMaze() throws MazeGenerationException {
        MazeGenerator generator = switch (generationType.toLowerCase()) {

            case "perfect" -> switch (generatorMethod.toLowerCase()) {
                case "simple" -> new SimplePerfectMazeGenerator(maze, width, height);
                case "graph" -> new GraphPerfectMazeGenerator(width, height, maze);
                default -> throw new IllegalStateException("Unexpected value: " + generatorMethod.toLowerCase());
            };
            case "imperfect" -> switch (generatorMethod.toLowerCase()) {
                case "simple" -> new SimpleImperfectMazeGenerator(maze, width, height);
                case "graph" -> new GraphImperfectMazeGenerator(width, height, maze);
                default -> throw new MazeGenerationException("Méthode de génération invalide.");
            };

            default -> throw new MazeGenerationException("Type de labyrinthe invalide.");
        };
    }
    public void initMaze() {
        for (int id = 0; id < maze.length; id++) {
            maze[id] = new Cell(id);
        }

    }


    public void printMaze(){
        maze[0].removeWall(0); // Entry
        maze[maze.length - 1].removeWall(2); // Exit

        int id = 0;
        for (int y = 0; y < height; y++) {
            StringBuilder row1 = new StringBuilder();
            StringBuilder row2 = new StringBuilder();
            StringBuilder row3 = new StringBuilder();
            for (int x = 0; x < width; x++) {
                String[] strCell = maze[x + id].getBody();
                row1.append(strCell[0]);
                row2.append(strCell[1]);
                row3.append(strCell[2]);
            }
            id += width; // next row
            System.out.println(row1 + "\n" + row2 + "\n" + row3);
        }
    }
}



