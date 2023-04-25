import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Robot implements Runnable {

    private final String name;
    private boolean running = true;
    private final ExplorationMap map;
    private final Random rand = new Random();
    private boolean hasStartingPoint = false;
    private int localRow;
    private int localCol;
    private boolean paused = false;
    private List<Token> extractedTokens = new ArrayList<>();

    public Robot(String name, ExplorationMap map) {
        this.name = name;
        this.map = map;
    }

    public List<Token> getExtractedTokens() {
        return extractedTokens;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void pause() {
        paused = true;
        synchronized (this) {
            notify();
        }
    }

    public void resume() {
        paused = false;
        synchronized (this) {
            notify();
        }
    }

    private Cell getAdjacentUnvisitedCell(int i, int j) {
        if (i > 0 && !map.getCell(i - 1, j).isVisited()) {
            return map.getCell(i - 1, j);
        }
        if (j > 0 && !map.getCell(i, j - 1).isVisited()) {
            return map.getCell(i, j - 1);
        }
        if (i < map.getSize() - 1 && !map.getCell(i + 1, j).isVisited()) {
            return map.getCell(i + 1, j);
        }
        if (j < map.getSize() - 1 && !map.getCell(i, j + 1).isVisited()) {
            return map.getCell(i, j + 1);
        }
        return null;
    }

    private Cell getRandomUnvisitedCell() {
        List<Cell> unvisitedCells = new ArrayList<>();
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = map.getCell(i, j);
                if (!cell.isVisited()) {
                    unvisitedCells.add(cell);
                }
            }
        }
        if (unvisitedCells.isEmpty()) {
            return null;
        } else {
            return unvisitedCells.get(rand.nextInt(unvisitedCells.size()));
        }
    }

    @Override
    public void run() {
        while (running) {
            if (!paused) {
                if (map.isFullyExplored()) {
                    this.stop();
                }
                Cell cell;
                if (!hasStartingPoint) {
                    cell = getRandomUnvisitedCell();
                    localRow = getRowForCell(cell);
                    localCol = getColForCell(cell);
                    hasStartingPoint = true;
                } else {
                    cell = getAdjacentUnvisitedCell(localRow, localCol);
                    if (cell == null)
                        cell = getRandomUnvisitedCell();
                    localRow = getRowForCell(cell);
                    localCol = getColForCell(cell);
                }
                if (cell != null) {
                    int row = getRowForCell(cell);
                    int col = getColForCell(cell);
                    boolean visited = map.visit(row, col, this);
                    if (!visited) {
                        System.out.println("Cannot visit cell located at row " + row + " and column " + col);
                    }
                }
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private int getRowForCell(Cell cell) {
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map.getCell(i, j) == cell) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getColForCell(Cell cell) {
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map.getCell(i, j) == cell) {
                    return j;
                }
            }
        }
        return -1;
    }

    public String getName() {
        return this.name;
    }

    public void printExtractedTokens() {
        System.out.println("Robot " + this.name + " has extracted " + this.getExtractedTokens().size() + " tokens.");
    }
}
