import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter map size: ");
        int n = scanner.nextInt();

        var explore = new Exploration(n);
        explore.addRobot(new Robot("Robot1", explore));
        explore.addRobot(new Robot("Robot2", explore));
        explore.addRobot(new Robot("Robot3", explore));
        explore.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("I was interrupted!");
            e.printStackTrace();
        }
        System.out.println(explore.getMap().toString());
        explore.interrupt();
    }

}
