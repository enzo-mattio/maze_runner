package maze;

import maze.exception.MazeGenerationException;

public class MazeRunner {
    public static void main(String[] args) {
        // Validez les arguments et générez le labyrinthe en utilisant le générateur approprié
        if (args.length != 4) {
            System.err.println("Erreur : Veuillez fournir une largeur, une hauteur, un type de labyrinthe et une méthode de génération valides.");
            System.out.println("Utilisation : java -jar maze.MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
            System.exit(1);
        }
        try {
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            String generationType = args[2];
            String generatorMethod = args[3];

            new Maze(width, height, generationType, generatorMethod);
        } catch (NumberFormatException e) {
            System.err.println("Erreur : Veuillez fournir une largeur et une hauteur valides.");
        } catch (MazeGenerationException e) {
            throw new RuntimeException(e);
        }
    }
}
