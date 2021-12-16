package manfred.end.design.pattern.black.board.v2.heartbeat;


import manfred.end.design.pattern.black.board.v2.pacemaker.PacemakerController;

/**
 * Fault monitor exists as a helper which receives and determines how to handle errors
 * found by heartbeat receiver
 */
public class FaultMonitor {

    public static void notify(String errorMessage) {
        switch (errorMessage) {
            case "Heartbeat sender is dead":
                System.err.println("Fault monitor: Heartbeat sender dead status received. " +
						"Initiating recovery mode...");
                PacemakerController.initiateRecovery();
                PacemakerController.updateGUI();
                break;
        }
    }


}