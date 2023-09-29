package maze;

public class Cell {

    // maze.Cell properties
    //  maze.Cell id
    public int y;
    // width = 3;
    // height = 3;
    public int x;

    private final boolean[] wall = {true, true, true, true}; // Top, Right, Bottom, Left

    // Directions
    int isouth = 7;
    int ieast = 5;
    int inorth = 1;
    int iwest = 3;



    // maze.Cell visited
    boolean visited = false;

    // maze.Cell constructor
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // maze.Cell methods
    public void setVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return !visited;
    }
    public String[] getBody() {
        String top = wall[0] ? "#" : ".";
        String right = wall[1] ? "#" : ".";
        String bot = wall[2] ? "#" : ".";
        String left = wall[3] ? "#" : ".";
        String[] cell = new String[3];
        cell[0] = "#" + top + "#";
        cell[1] = left + "." + right;
        cell[2] = "#" + bot + "#";
        return cell;
    }

    public void removeWall(int direction) {
        wall[direction] = false;
    }
    public boolean hasWall(int direction) {
        return wall[direction];
    }


}
