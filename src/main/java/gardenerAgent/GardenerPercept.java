package gardenerAgent;

import agent.Agent;
import agent.Percept;

public class GardenerPercept extends Percept {
    protected boolean flower;
    protected boolean fence;

    /**
     * Constructs a garden world percept. If the agent is in a square that has
     * a flower, then creates a percept that sees the plant.
     */
    public GardenerPercept(GardenerState state, Agent agent) {

        super(state, agent);

        int x, y;

        x = agent.getAgentX();
        y = agent.getAgentY();
        int dir = agent.getAgentDir();

        /** determine flower */

        flower = state.isFlower(x, y);

        /** determine fence */
        int viewX = x + Direction.DELTA_X[dir];
        int viewY = y + Direction.DELTA_Y[dir];
        fence = state.isFence(viewX, viewY);

    }

    /**
     * Returns true if the percept reflects that the agent is on a flower square.
     */
    public boolean seeFlower() {
        return flower;
    }

    /**
     * Returns true if the percept reflects that the square immediately in front
     * of the agent contains an obstacle.
     */
    public boolean seeFence() {
        return fence;
    }

    public String toString() {

        StringBuffer pstring;

        pstring = new StringBuffer(5);
        if (flower)
            pstring.append("FLOWER ");
        else if (fence)
            pstring.append("FENCE ");
        else
            pstring.append("CLEAR ");
        return pstring.toString();
    }
}
