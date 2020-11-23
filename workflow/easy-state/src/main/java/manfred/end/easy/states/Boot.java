package manfred.end.easy.states;

import org.jeasy.states.api.*;
import org.jeasy.states.core.FiniteStateMachineBuilder;
import org.jeasy.states.core.TransitionBuilder;

import java.util.HashSet;
import java.util.Set;

public class Boot {
    public static void main(String[] args) throws FiniteStateMachineException {
        // 1. First, let's define states
        State locked = new State("locked");
        State unlocked = new State("unlocked");

        Set<State> states = new HashSet<>();
        states.add(locked);
        states.add(unlocked);

        // 2. Then, define events
        class PushEvent extends AbstractEvent {
        }
        class CoinEvent extends AbstractEvent {
        }

        // 3. Then transitions
        class Unlock implements EventHandler {

            @Override
            public void handleEvent(Event event) throws Exception {
                System.out.println(event.getClass());
            }
        }

        class Lock implements EventHandler {

            @Override
            public void handleEvent(Event event) throws Exception {
                System.out.println(event.getClass());
            }
        }


        Transition unlock = new TransitionBuilder()
                .name("unlock")
                .sourceState(locked)
                .eventType(CoinEvent.class)
                .eventHandler(new Unlock())
                .targetState(unlocked)
                .build();

        Transition pushLocked = new TransitionBuilder()
                .name("pushLocked")
                .sourceState(locked)
                .eventType(PushEvent.class)
                .targetState(locked)
                .build();

        Transition lock = new TransitionBuilder()
                .name("lock")
                .sourceState(unlocked)
                .eventType(PushEvent.class)
                .eventHandler(new Lock())
                .targetState(locked)
                .build();

        Transition coinUnlocked = new TransitionBuilder()
                .name("coinUnlocked")
                .sourceState(unlocked)
                .eventType(CoinEvent.class)
                .targetState(unlocked)
                .build();

        // 4. And finally the finite state machine
        FiniteStateMachine turnstileStateMachine = new FiniteStateMachineBuilder(states, locked)
                .registerTransition(lock)
                .registerTransition(pushLocked)
                .registerTransition(unlock)
                .registerTransition(coinUnlocked)
                .build();

        turnstileStateMachine.fire(new CoinEvent());
    }
}
