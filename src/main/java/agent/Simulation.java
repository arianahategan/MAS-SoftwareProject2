package agent;

import java.util.concurrent.CountDownLatch;

/**
 * The top-level class for an agent simulation. This can be used for either
 * single or multi-agent simulations.
 */
public abstract class Simulation {

    protected Agent agent = null;
    protected Environment env;


    /**
     * Constructs a new simulation. Initializes the agent(or agents vector) and
     * the environment.
     */
    public Simulation(Environment e) {
        env = e;
    }

    /**
     * Runs the simulation starting from a given state. This consists of a
     * sense-act loop for the/(each) agent. An alternative approach would be to
     * allow the agent to decide when it will sense and act.
     */
    public void start(State initState) throws InterruptedException {
        env.setInitialState(initState);
        env.currentState().display(null, "", "");

        CountDownLatch countDownLatch = new CountDownLatch(env.currentState().getAgents().size());


        for (Agent a : env.currentState().getAgents()
        ) {
            Thread thread = new Thread(() -> {
                while (!isComplete()) {
                    Percept percept = env.getPercept(a);
                    a.see(percept);
                    Action action = a.selectAction();
                    env.updateState(a, action);
                    env.currentState().display(a, action.toString(), percept.toString());
                }
                countDownLatch.countDown();
            });

            thread.start();
        }
        countDownLatch.await();

        System.out.println("Performance measure: " + performanceMeasure() + " actions to water 10 flowers.");
    }

    /**
     * Is the simulation over? Returns true if it is, otherwise false.
     */
    protected abstract boolean isComplete();

    /**
     * Simulation performance measure - number of actions performed to complete the task.
     */
    protected abstract int performanceMeasure();

}
