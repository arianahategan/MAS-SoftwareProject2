package gardenerAgent;

import agent.Action;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GoForwardTest {
    @Test
    public void shouldGoForwardToSouth() {
        GardenerState initState = GardenerState.getInitState();
        Action goForward = new GoForward();
        goForward.execute(initState.gardenerAgents.get(0), initState);
        assertEquals(1, initState.gardenerAgents.get(0).getAgentX());
        assertEquals(2, initState.gardenerAgents.get(0).getAgentY());
    }

    @Test
    public void shouldGoForwardToNorth() {
        GardenerState initState = GardenerState.getInitState();
        Action goForward = new GoForward();
        goForward.execute(initState.gardenerAgents.get(1), initState);
        assertEquals(6, initState.gardenerAgents.get(1).getAgentX());
        assertEquals(5, initState.gardenerAgents.get(1).getAgentY());
    }

    @Test
    public void shouldGoForwardToWest() {
        GardenerState initState = GardenerState.getInitState();
        Action turnRight = new TurnRight();
        turnRight.execute(initState.gardenerAgents.get(1), initState);
        turnRight.execute(initState.gardenerAgents.get(1), initState);
        turnRight.execute(initState.gardenerAgents.get(1), initState);
        Action goForward = new GoForward();
        goForward.execute(initState.gardenerAgents.get(1), initState);
        assertEquals(5, initState.gardenerAgents.get(1).getAgentX());
        assertEquals(6, initState.gardenerAgents.get(1).getAgentY());
    }

    @Test
    public void shouldGoForwardToEast() {
        GardenerState initState = GardenerState.getInitState();
        Action turnRight = new TurnRight();
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        Action goForward = new GoForward();
        goForward.execute(initState.gardenerAgents.get(0), initState);
        assertEquals(2, initState.gardenerAgents.get(0).getAgentX());
        assertEquals(1, initState.gardenerAgents.get(0).getAgentY());
    }
}
