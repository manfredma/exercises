package manfred.end.design.pattern.black.board.v2.heartbeat;

/**
 * HeartbeatReceiver is the component in the Heartbeat Tactic which checks
 * for aliveness signals from the Heartbeat Sender at specified intervals.
 */
public class HeartbeatReceiver implements Runnable {
    // The frequency at which we will check for a heartbeat sender update in
    // milliseconds
    private static long checkingInterval;

    // The length of time a heartbeat sender can be idle before we declare it
    // dead
    private static long expireTime;

    // The last time we received a heartbeat sender aliveness message
    private static long lastUpdatedTime;

    // The time at which we check for the aliveness of the heartbeat sender
    private static long checkingTime;

    public HeartbeatReceiver(long checkingInterval, long expireTime) {
        HeartbeatReceiver.checkingInterval = checkingInterval;
        HeartbeatReceiver.expireTime = expireTime;
    }

    public static void updateTime(long updateTime) {
        lastUpdatedTime = updateTime;
    }

    // Checks at the checkingInterval if the heartbeat sender has been sending heartbeat messages.
    // Returns: false if a message has not been received within the expireTime limit. True
    // otherwise.
    private boolean checkAlive() {
        try {
            Thread.sleep(checkingInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        checkingTime = System.currentTimeMillis();

        return (checkingTime - lastUpdatedTime) <= expireTime;
    }

    @Override
    public void run() {
        while (true) {
            if (!checkAlive()) {
                FaultMonitor.notify("Heartbeat sender is dead");
            }
        }
    }
}