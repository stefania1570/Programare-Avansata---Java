import java.util.Random;

public class Robot extends Thread implements Runnable {
    private String name;
    private int row, col;
    private boolean running = true;
    private Random rand = new Random();
    private int [] movement = {-1, 0, 1};
    Exploration explore;

    /**
     * Constructor for the robot
     * @param name String
     * @param explore Exploration
     */
    public Robot(String name, Exploration explore) {
        this.explore = explore;
        this.name = name;
        this.row = rand.nextInt(explore.getMap().getSize());
        this.col = rand.nextInt(explore.getMap().getSize());
    }

    /**
     * Getter for the name of the robot
     * @return name String
     */
    public String getRobotName() {
        return this.name;
    }

    /**
     * Chooses the next row and col and visits it
     */
    public void run() {
        while (running) {
            row = ((row + movement[rand.nextInt(3)]) + explore.getMap().getSize()) % explore.getMap().getSize();
            col = ((col + movement[rand.nextInt(3)]) + explore.getMap().getSize()) % explore.getMap().getSize();
            explore.getMap().visit(row, col, this);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}
