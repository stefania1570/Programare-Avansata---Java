import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exploration {
    private SharedMemory mem;
    private ExplorationMap map;
    private List<Robot> robots = new ArrayList<>();

    public Exploration(int size) {
        this.mem = new SharedMemory(size * size * size);
        this.map = new ExplorationMap(size, mem);
    }

    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command:\n-pause -> pauses all robots\n-resume -> resumes all robots\n-pause <robot_name> -> pauses a specific robot\n-resume <robot_name> -> resumes a specific robot\n");
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        if (tokens.length == 1) {
            if (tokens[0].equals("pause")) {
                pauseAllRobots();
            } else if (tokens[0].equals("resume")) {
                resumeAllRobots();
            } else if (tokens[0].equals("stop")) {
                stopAllRobots();
            } else {
                System.out.println("Invalid command!");
            }
        } else if (tokens.length == 2) {
            // pause specific robot
            if (tokens[0].equals("pause")) {
                String name = tokens[1];
                pauseRobot(name);
            } else if (tokens[0].equals("resume")) {
                String name = tokens[1];
                resumeRobot(name);
            } else {
                System.out.println("Invalid command!");
            }
        } else {
            System.out.println("Invalid command!");
        }
    }

    public void start() {
        for (Robot robot : robots) {
            Thread thread = new Thread(robot);
            thread.start();
        }

    }

    public void pauseAllRobots() {
        for (Robot r : robots) {
            r.pause();
        }
    }

    public void stopAllRobots() {
        for (Robot r : robots) {
            r.stop();
        }
    }

    public void resumeAllRobots() {
        for (Robot r : robots) {
            r.resume();
        }
    }

    public void pauseRobot(String name) {
        for (Robot r : robots) {
            if (r.getName().equals(name)) {
                r.pause();
                return;
            }
        }
        System.out.println("Robot not found.");
    }

    public void resumeRobot(String name) {
        for (Robot r : robots) {
            if (r.getName().equals(name)) {
                r.resume();
                return;
            }
        }
        System.out.println("Robot not found.");
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public ExplorationMap getMap() {
        return map;
    }

    public void addRobot(Robot r) {
        robots.add(r);
    }

}
