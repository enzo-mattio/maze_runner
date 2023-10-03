package maze;

import maze.exception.MazeGenerationException;

public class MazeRunner {
    public static void main(String[] args) {
        try {
            if (args.length != 4) { // checks if the number of arguments is correct
                howToUse();
                return;
            }

            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            String generationType = args[2];
            String generatorMethod = args[3];

            if (width < 5 || height < 5) {
                System.err.println("Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5");
                howToUse();
                return;
            }

            if (!isValidGenerationType(generationType) || !isValidGeneratorMethod(generatorMethod)) {
                System.err.println("Erreur : Veuillez fournir un type de labyrinthe et une méthode de génération valides.");
                howToUse();
                return;
            }

            new Maze(width, height, generationType, generatorMethod);
        } catch (NumberFormatException e) {
            System.err.println("Erreur : Veuillez fournir une largeur et une hauteur valides supérieurs à 5");
            howToUse();
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | OutOfMemoryError | MazeGenerationException e) {
            // Prevents crash if the user provides a W/L that is too large
            System.err.println("Erreur inattendue lors de la génération du labyrinthe. Veuillez réessayer.");
        }
    }

    private static void howToUse() {
        System.err.println("Utilisation : java -jar MazeRunner.jar [largeur] [hauteur] [perfect/imperfect] [simple/graph/optimized]");
    }

    private static boolean isValidGenerationType(String type) {
        return type.equals("perfect") || type.equals("imperfect");
    }

    private static boolean isValidGeneratorMethod(String method) {
        return method.equals("simple") || method.equals("graph") || method.equals("optimized");
    }
}