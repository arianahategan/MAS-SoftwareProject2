package gardenerAgent;

import agent.Simulation;

public class GardenerSimulation extends Simulation {

    public GardenerSimulation(GardenerEnvironment env) {
        super(env);
    }

    @Override
    protected boolean isComplete() {
        return ((GardenerState) (env.currentState())).getNumFlowerLocs() == 0;
    }

    @Override
    protected int performanceMeasure() {
        return ((GardenerState) (env.currentState())).numberOfTrials;
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("START OF SIMULATION");
        System.out.println("-----------------------------------");
        System.out.println();


        GardenerEnvironment env = new GardenerEnvironment();
        GardenerSimulation sim = new GardenerSimulation(env);
        GardenerState initState = GardenerState.getInitState();

        /** starts simulation*/
        sim.start(initState);

    }
}
