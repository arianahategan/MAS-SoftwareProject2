package gardenerAgent;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GardenerAgentTest {


    @Test
    public void shouldTurnRight() {
        GardenerAgent gardenerAgent = new GardenerAgent(1, 1, 1, 1);
        gardenerAgent.setFlower(false);
        gardenerAgent.setFence(true);
        assertTrue(gardenerAgent.selectAction() instanceof TurnRight);
    }

    @Test
    public void shouldNotWaterFlower() {
        GardenerAgent gardenerAgent = new GardenerAgent(1, 1, 1, 1);
        gardenerAgent.setFlower(true);
        SharedMemory.getInstance().addWateredPlant(1, 1);
        assertFalse(gardenerAgent.selectAction() instanceof WaterFlower);
    }

    @Test
    public void shouldWaterFlower() {
        GardenerAgent gardenerAgent = new GardenerAgent(2, 1, 1, 1);
        gardenerAgent.setFlower(true);
        assertTrue(gardenerAgent.selectAction() instanceof WaterFlower);
    }

}
