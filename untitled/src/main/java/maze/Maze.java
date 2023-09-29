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
                case "simple" -> new SimplePerfectMazeGenerator(width, height, maze);
                case "graph" -> new GraphPerfectMazeGenerator(width, height, maze);
                default -> throw new IllegalStateException("Unexpected value: " + generatorMethod.toLowerCase());
            };
            case "imperfect" -> switch (generatorMethod.toLowerCase()) {
                case "simple" -> new SimpleImperfectMazeGenerator(width, height, maze);
                case "graph" -> new GraphImperfectMazeGenerator(width, height, maze);
                default -> throw new MazeGenerationException("Méthode de génération invalide.");
            };

            default -> throw new MazeGenerationException("Type de labyrinthe invalide.");
        };
    }
    public void initMaze() {
        // Initialisation avec l'entrée et la sortie
        int id = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                maze[id] = new Cell(x, y);
                id++;
            }
        }

    }


    public void printMaze(){
        // Affichage du labyrinthe
        maze[0].removeWall(0);
        maze[maze.length - 1].removeWall(2);
        int delta = 0;
        for (int y = 0; y < height; y++) {
            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();
            StringBuilder str3 = new StringBuilder();
            for (int x = 0; x < width; x++) {
                String[] strs = maze[x + delta].getBody();
                str1.append(strs[0]);
                str2.append(strs[1]);
                str3.append(strs[2]);
            }
            delta += width;
            System.out.println(str1 + "\n" + str2 + "\n" + str3);
        }
    }
}


