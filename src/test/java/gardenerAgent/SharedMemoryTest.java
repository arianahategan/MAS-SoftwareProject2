package gardenerAgent;

import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SharedMemoryTest {
    @Test
    public void shouldSetTheRightTime() {
        SharedMemory.getInstance().addWateredPlant(1, 1);
        int x = -1;
        for (Map.Entry<Pair, Integer> wateredFlower : SharedMemory.wateredFlowers.entrySet()) {
            if(wateredFlower.getKey().getX() == 1 && wateredFlower.getKey().getY() == 1)
                x = wateredFlower.getValue();
        }
        assertEquals(3, x);
    }

    @Test
    public void shouldDecreaseTime() {
        SharedMemory.getInstance().addWateredPlant(2, 1);
        int x = -1;
        for (Map.Entry<Pair, Integer> wateredFlower : SharedMemory.wateredFlowers.entrySet()) {
            if(wateredFlower.getKey().getX() == 2 && wateredFlower.getKey().getY() == 1)
                x = wateredFlower.getValue();
        }
        assertEquals(3, x);
        SharedMemory.getInstance().simulateTime();
        for (Map.Entry<Pair, Integer> wateredFlower : SharedMemory.wateredFlowers.entrySet()) {
            if(wateredFlower.getKey().getX() == 2 && wateredFlower.getKey().getY() == 1)
                x = wateredFlower.getValue();
        }
        assertEquals(2, x);
    }

    @Test
    public void shouldBeWatered(){
        SharedMemory.getInstance().addWateredPlant(1, 1);
        assertTrue(SharedMemory.getInstance().isWatered(new Pair(1, 1)));
    }

    @Test
    public void shouldStillBeWatered(){
        SharedMemory.getInstance().addWateredPlant(1, 1);
        SharedMemory.getInstance().simulateTime();
        SharedMemory.getInstance().simulateTime();
        assertTrue(SharedMemory.getInstance().isWatered(new Pair(1, 1)));
    }

    @Test
    public void shouldNotBeWatered(){
        SharedMemory.getInstance().addWateredPlant(1, 1);
        SharedMemory.getInstance().simulateTime();
        SharedMemory.getInstance().simulateTime();
        SharedMemory.getInstance().simulateTime();
        assertFalse(SharedMemory.getInstance().isWatered(new Pair(1, 1)));
    }
}
