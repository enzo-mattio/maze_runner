package maze;

public class Cell {

    // maze.Cell properties
    //  maze.Cell id
    public int id;

    private final boolean[] wall = {true, true, true, true}; // Top, Right, Bottom, Left


    // maze.Cell visited
    boolean visited = false;

    // maze.Cell constructor
    public Cell(int id) {
        this.id = id;
    }

    // maze.Cell methods

    public String[] getBody() {
        return new String[]{"#" + (wall[0] ? "#" : ".") + "#", (wall[3] ? "#" : ".") + "." + (wall[1] ? "#" : "."), "#" + (wall[2] ? "#" : ".") + "#"};

    }
    public void setVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }
    public void removeWall(int direction) {
        wall[direction] = false;
    }
    public boolean hasWall(int direction) {
        return wall[direction];
    }


}
