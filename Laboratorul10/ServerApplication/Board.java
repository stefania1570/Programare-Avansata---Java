public class Board {
    private static final int SIZE = 15;
    private static final char EMPTY = '+';
    private char[][] cells;
    public Board() {
        cells = new char[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = EMPTY;
            }
        }
    }

    public synchronized void makeMove(String move, char symbol) {
        int row = parseRow(move);
        int col = parseCol(move);
        cells[row][col] = symbol;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private int parseRow(String move) {
        // Parse the row from the move string (ex "A3" => 2)
        char rowChar = move.charAt(0);
        return Character.toUpperCase(rowChar) - 'A';
    }

    private int parseCol(String move) {
        // Parse the column from the move string (ex "A3" => 2)
        String colStr = move.substring(1);
        return Integer.parseInt(colStr) - 1;
    }


    public String getBoardState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(cells[i][j]);
            }
            sb.append(System.lineSeparator()); //TODO AICI E PROBL ca nu imi scrie tot?
        }
        return sb.toString();
    }

    public boolean isValidMove(String move){
        if(Character.isLetter(move.charAt(0)) && Character.isDigit(move.charAt(1))){
            // Check if the targeted cell is empty
            if (cells[parseRow(move)][parseCol(move)] == EMPTY) {
                return true;
            }
        }
        return false;
    }

}
