package gardenerAgent;

import agent.Action;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnRightTest {
    @Test
    public void shouldTurnRight() {
        GardenerState initState = GardenerState.getInitState();
        Action turnRight = new TurnRight();
        turnRight.execute(initState.gardenerAgents.get(0), initState);
        assertEquals(3, initState.gardenerAgents.get(0).getAgentDir());
    }
}
