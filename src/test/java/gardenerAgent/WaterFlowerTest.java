package gardenerAgent;

import agent.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WaterFlowerTest {
    @Test
    public void shouldTurnRight() {
        GardenerState initState = GardenerState.getInitState();
        Action waterFlower = new WaterFlower();
        Action goForward = new GoForward();
        Action turnRight = new TurnRight();
        goForward.execute(initState.gardenerAgents.get(0), initState);
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        goForward.execute(initState.gardenerAgents.get(0), initState);
        waterFlower.execute(initState.gardenerAgents.get(0), initState);
        assertTrue(SharedMemory.getInstance().isWatered(new Pair(initState.gardenerAgents.get(0).getAgentX(),
                initState.gardenerAgents.get(0).getAgentY())));
    }
}
