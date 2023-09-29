package maze;

public class Edge {
    private final int cellA;
    private final int cellB;

    public Edge(int cellA, int cellB) {
        this.cellA = cellA;
        this.cellB = cellB;
    }

    public int getCellA() {
        return cellA;
    }

    public int getCellB() {
        return cellB;
    }
}
