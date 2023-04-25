public class Main {
    public static void main(String[] args) {
        int size = 5;
        int timeLimit = 50000;
        Exploration exploration = new Exploration(size);

        Robot robot1 = new Robot("R1", exploration.getMap());
        Robot robot2 = new Robot("R2", exploration.getMap());
        Robot robot3 = new Robot("R3", exploration.getMap());
        exploration.addRobot(robot1);
        exploration.addRobot(robot2);
        exploration.addRobot(robot3);

        DaemonThread daemonThread = new DaemonThread(exploration, timeLimit);
        daemonThread.start();

        exploration.start();

        while (true) {
            exploration.handleInput();

            if (exploration.getMap().isFullyExplored()) {
                System.out.println("Done!\n");
                break;
            } else if (daemonThread.isTimedOut()) {
                System.out.println("Exploration timed out!");
                break;
            }
        }
        for (Robot r : exploration.getRobots()) {
            r.printExtractedTokens();
        }
    }
}