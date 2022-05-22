package gardenerAgent;


import agent.Action;
import agent.Agent;
import agent.Percept;
import lombok.Setter;

@Setter
public class GardenerAgent extends Agent {

    private boolean flower;
    private boolean fence;

    public GardenerAgent(int x, int y, int dir, int id) {
        super(x, y, dir, id);
    }

    @Override
    public void see(Percept p) {
        flower = ((GardenerPercept) p).seeFlower();
        fence = ((GardenerPercept) p).seeFence();
    }

    /**
     * Returns the agent's X position.
     */
    public int getAgentX() {
        return agentX;
    }

    /**
     * Returns the agent's Y position.
     */
    public int getAgentY() {
        return agentY;
    }

    /**
     * Returns the agent's orientation. Note that the constants in the Direction
     * class are used to interpret the meaning of this result.
     */
    public int getAgentDir() {
        return agentDir;
    }

    /**
     * Returns the agent's id.
     */
    public int getAgentID() {
        return agentID;
    }

    /**
     * Returns a string the represents the agent's orientation.
     */
    public String getAgentDirString() {
        return Direction.toString(agentDir);
    }

    /**
     * Changes the agent's X position.
     */
    public void setAgentX(int x) {
        agentX = x;
    }

    /**
     * Changes the agent's Y position.
     */
    public void setAgentY(int y) {
        agentY = y;
    }

    /**
     * Changes the agent's orientation.
     */
    public void setAgentDir(int dir) {
        agentDir = dir;
    }

    public void Start() {

    }

    public Action selectAction() {
        SharedMemory.getInstance().simulateTime();

        /** The agent action selection function */
        if (flower) {
            // agent's coordinates
            Pair p = new Pair(this.getAgentX(), this.getAgentY());
            // check if the flower has been watered
            if (!SharedMemory.getInstance().isWatered(p)) {
                return new WaterFlower();
            } else if (fence)
                return new TurnRight();
            /** The agent has no strategy, it selects randomly an action */
            return getAction();
        }
        if (fence)
            return new TurnRight();
        /** The agent has no strategy, it selects randomly an action */
        return getAction();
    }

    private Action getAction() {
        double rnd = Math.random();
        int r = 0;
        if (rnd < 0.3)
            r = 1;
        return switch (r) {
            case 0 -> new GoForward();
            case 1 -> new TurnRight();
            default -> null;
        };
    }


}
