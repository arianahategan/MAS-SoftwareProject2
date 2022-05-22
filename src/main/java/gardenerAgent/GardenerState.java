package gardenerAgent;

import agent.Agent;
import agent.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GardenerState extends State {
    private static final Lock lock = new ReentrantLock();

    private static final int CLEAR = 0;
    private static final int FLOWER = 1;
    private static final int FENCE = 2;
    private static final int GARDENER = 3;

    /*
     * Default map for initial state. The room is completely surrounded by a fence
     */
    protected static int[][] defaultMap = {
            {FENCE, FENCE, FENCE, FENCE, FENCE, FENCE, FENCE, FENCE},
            {FENCE, GARDENER, CLEAR, CLEAR, CLEAR, CLEAR, CLEAR, FENCE},
            {FENCE, CLEAR, FLOWER, CLEAR, CLEAR, CLEAR, FLOWER, FENCE},
            {FENCE, CLEAR, CLEAR, CLEAR, CLEAR, CLEAR, CLEAR, FENCE},
            {FENCE, CLEAR, CLEAR, CLEAR, CLEAR, CLEAR, CLEAR, FENCE},
            {FENCE, CLEAR, FLOWER, CLEAR, CLEAR, CLEAR, CLEAR, FENCE},
            {FENCE, CLEAR, CLEAR, FLOWER, CLEAR, CLEAR, GARDENER, FENCE},
            {FENCE, FENCE, FENCE, FENCE, FENCE, FENCE, FENCE, FENCE}};

    protected List<Agent> gardenerAgents;

    public List<Agent> getAgents() {
        return gardenerAgents;
    }

    /**
     * An array that contains the locations of objects in the world.
     */
    protected int[][] map;

    protected int numFlowerLocs = 10;

    protected int height;
    protected int width;

    public int numberOfTrials;

    /**
     * Returns the default initial state for the garden.
     */
    public static GardenerState getInitState() {

        GardenerState state;
        state = new GardenerState();
        state.gardenerAgents = new ArrayList<>();
        state.width = defaultMap.length;
        state.height = defaultMap[0].length;
        state.map = new int[state.width][state.height];
        state.map = defaultMap;
        GardenerAgent g1 = new GardenerAgent(1, 1, 2, 1);
        GardenerAgent g2 = new GardenerAgent(6, 6, 0, 2);
        state.gardenerAgents.add(g1);
        state.gardenerAgents.add(g2);
        state.numberOfTrials = 0;
        return state;
    }


    /**
     * Constructs a new gardener state.
     */
    public GardenerState() {
    }

    /**
     * Waters a flower from the specified location
     */
    public void waterFlower(int x, int y) {
        lock.lock();
        if (map[x][y] == FLOWER) {
            SharedMemory blackboard = SharedMemory.getInstance();
            blackboard.addWateredPlant(x, y);
            numFlowerLocs--;
        }
        lock.unlock();
    }

    /**
     * Returns true if the specified location has a flower on it.
     */
    public boolean isFlower(int x, int y) {
        lock.lock();
        if (map[x][y] == FLOWER) {
            lock.unlock();
            return true;
        } else {
            lock.unlock();
            return false;
        }

    }

    /**
     * Returns true if the specified location is part of the fence.
     */
    public boolean isFence(int x, int y) {
        return map[x][y] == FENCE;
    }


    /**
     * Returns true if the location is within bounds of the state's map.
     */
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Returns the number of watering actions needed to end the task.
     */
    public int getNumFlowerLocs() {
        return numFlowerLocs;
    }

    /**
     * Prints an output of the state to the screen. This output includes a map as
     * well as information about the agent's location and the direction it is
     * facing. On the map, "A" denotes the agent and "*" denotes a flower.
     */
    public void display(Agent agent, String action, String percept) {

        lock.lock();
        if (agent != null)
            System.out.println("Agent with ID " + agent.getAgentID());

        for (int j = 1; j < width - 1; j++)
            System.out.print("  " + j);
        System.out.println();

        System.out.print(" ");
        for (int j = 1; j < width - 1; j++)
            System.out.print("+--");
        System.out.println("+");

        /**
         * To print to the screen properly, the loop in the Y direction must be
         * the outer loop.
         */
        for (int i = 1; i < height - 1; i++) {
            System.out.print(i + "|");
            for (int j = 1; j < width - 1; j++) {
                boolean hasAgent = false;
                for (Agent a : gardenerAgents) {
                    if (i == a.getAgentY() && j == a.getAgentX()) {
                        hasAgent = true;
                    }
                }
                if (hasAgent) {
                    System.out.print("A");
                } else if (isFlower(i, j))
                    System.out.print("*");
                else
                    System.out.print(" ");
                System.out.print(" |");
            }
            System.out.println();

            System.out.print(" +");
            for (int j = 1; j < width - 2; j++)
                System.out.print("--+");
            System.out.println("--+");

        }
        if (agent != null) {
            System.out.println("Location: (" + agent.getAgentX() + "," + agent.getAgentY() + ")  "
                    + "\nFacing: " + agent.getAgentDirString());
            System.out.println("Action: " + action);
            System.out.println("Percept: " + percept);
            System.out.println();
        }

        lock.unlock();

    }
}
