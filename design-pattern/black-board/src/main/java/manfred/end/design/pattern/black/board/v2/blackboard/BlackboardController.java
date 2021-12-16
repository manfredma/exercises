package manfred.end.design.pattern.black.board.v2.blackboard;

import manfred.end.design.pattern.black.board.v2.pacemaker.ActivityStatusCalculator;
import manfred.end.design.pattern.black.board.v2.pacemaker.BpmCalculator;
import manfred.end.design.pattern.black.board.v2.pacemaker.PulseStatusCalculator;

/**
 * BlackboardController defines the order in which different knowledge sources
 * interacts with and modifies knowledge written on the Blackboard.
 * Currently, it is following a very simple process of going through each
 * component in the order defined in the loop() method and letting them
 * update the Blackboard one after another.
 */


public class BlackboardController {

    private static final BpmCalculator bpm = new BpmCalculator();

    private static final PulseStatusCalculator pulseStatus = new PulseStatusCalculator();

    private static final ActivityStatusCalculator activityStatus = new ActivityStatusCalculator();

    // Loop through the following knowledge sources and have them update the
    // knowledge written on the Blackboard.
    public static void loop() {
        bpm.updateBlackboard();
        pulseStatus.updateBlackboard();
        activityStatus.updateBlackboard();
    }
}