import java.util.List;

public class ExplorationMap {
    private  Cell[][] matrix;
    private int size;

    /**
     * Constructor for the exploration map
     * @param n int = the size of the map
     */
    public ExplorationMap(int n) {
        this.size = n;
        this.matrix = new Cell[n][n];
        for(int h = 0 ; h < n ; h++) {
            for(int z = 0; z < n; z++) {
                matrix[h][z] = new Cell();
            }
        }
    }

    /**
     * the robot extract tokens and store the tokens in the cell
     * @param row   the row
     * @param col   the column
     * @param robot the Robot that visits the cell
     */
    public void visit(int row, int col, Robot robot) {
        Cell cell = getMatrix()[row][col];
        synchronized (cell) {
            if(cell.getTokens().isEmpty()){
                List<Token> tokens = robot.explore.getMemory().extractTokens(1);
                for(Token t : tokens) {
                    cell.add(t);
                    System.out.println(robot.getRobotName() + " visited the cell [" + row + "][" + col + "] -> " +  t.toString());
                }
            }
        }
    }

    /**
     * Getter for the size
     * @return size int
     */
    public int getSize() {
        return size;
    }
    /**
     * Getter for the matrix
     * @return matrix
     */
    private Cell[][] getMatrix() {
        return this.matrix;
    }

    /**
     * Overridden to String method to show the matrix as a string
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(matrix[i][j].toString());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}