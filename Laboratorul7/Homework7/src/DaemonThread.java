public class DaemonThread extends Thread {
    private final Exploration exploration;
    private final long timeLimit;
    private boolean isTimedOut = false;

    public DaemonThread(Exploration exploration, long timeLimit) {
        this.exploration = exploration;
        this.timeLimit = timeLimit;
        setDaemon(true);
    }

    public boolean isTimedOut() {
        return isTimedOut;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= timeLimit) {
                //cand se depaseste limita de timp(50 secunde)
                isTimedOut = true;
                exploration.stopAllRobots();
                break;
            }
        }
    }
}
