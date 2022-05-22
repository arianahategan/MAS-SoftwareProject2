package gardenerAgent;


import agent.Action;
import agent.Agent;
import agent.Environment;
import agent.Percept;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A simulator for the garden environment.
 */
public class GardenerEnvironment extends Environment {

    private final Lock lock = new ReentrantLock();

    public GardenerEnvironment() {
    }

    /**
     * Creates a percept for an agent. This implements the see: S -> P
     * function.
     */
    protected Percept getPercept(Agent a) {
        GardenerPercept p;
        if (state instanceof GardenerState) {
            p = new GardenerPercept((GardenerState) state, (GardenerAgent) a);
            return p;
        } else {
            System.out.println("ERROR - state is not a DroneState object.");
            return null;
        }
    }

    /**
     * Executes an agent's action and update the environment's state.
     */
    protected void updateState(Agent a, Action action) {
        lock.lock();
        super.updateState(a, action);
        lock.unlock();
    }
}
