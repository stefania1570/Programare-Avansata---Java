import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;
    SharedMemory mem;

    public ExplorationMap(int size, SharedMemory sharedMemory) {
        this.matrix = new Cell[size][size];
        this.mem = sharedMemory;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return matrix.length;
    }

    public Cell getCell(int row, int col) {
        return matrix[row][col];
    }

    public boolean visit(int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if (!matrix[row][col].isVisited()) {
                List<Token> extractedTokens = mem.extractTokens(matrix.length - 1, robot);
                matrix[row][col].setTokens(extractedTokens);
                matrix[row][col].setVisited(true);
                System.out.println(robot.getName() + " visited cell [" + row + "," + col + "] and extracted tokens " + extractedTokens);
                return true;
            }
        }
        return false;
    }

    public int getVisitedCells() {
        int count = 0;
        int size = getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = getCell(i, j);
                if (cell.isVisited()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isFullyExplored() {
        int size = getSize();
        return getVisitedCells() == size * size;
    }


}
