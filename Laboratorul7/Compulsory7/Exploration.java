import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();

    /**
     * Constructor for the exploration
     * @param n int
     */
    Exploration(int n){
        mem = new SharedMemory(n);
        map = new ExplorationMap(n);
    }

    /**
     * Method that starts the robot threads
     */
    public void start() {
        for (Robot robot : robots) {
            robot.start();
        }
    }

    /**
     * Method that ends the robot threads
     */
    public void interrupt() {
        for (Robot robot : robots) {
            robot.interrupt();
        }
    }

    /**
     * Adds a robot to the list
     * @param robot Robot
     */
    public void addRobot(Robot robot) {
        this.robots.add(robot);
    }

    /**
     * Getter for the exploration map
     * @return map
     */
    public ExplorationMap getMap() {
        return this.map;
    }

    /**
     * Getter for the shared memory
     * @return shared memory
     */
    public SharedMemory getMemory() {
        return this.mem;
    }
}
