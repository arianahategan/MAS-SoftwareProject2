package gardenerAgent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedMemory {
    public static Map<Pair, Integer> wateredFlowers = new HashMap<>();
    private static final Lock lock = new ReentrantLock();

    private static SharedMemory instance;

    public SharedMemory() {
        wateredFlowers = new HashMap<>();
    }

    public static SharedMemory getInstance() {
        if (instance == null) {
            instance = new SharedMemory();
        }
        return instance;
    }

    public void addWateredPlant(int x, int y) {
        Pair flowerCoordinates = new Pair(x, y);
        wateredFlowers.put(flowerCoordinates, 3);
    }

    public boolean isWatered(Pair coordinates) {
        boolean result = false;
        for (Map.Entry<Pair, Integer> wateredFlower : wateredFlowers.entrySet()) {
            if (wateredFlower.getKey().getX() == coordinates.getX() &&
                    wateredFlower.getKey().getY() == coordinates.getY()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void simulateTime() {
        lock.lock();
        for (Map.Entry<Pair, Integer> wateredFlower : wateredFlowers.entrySet()) {
            wateredFlower.setValue(wateredFlower.getValue() - 1);
        }
        wateredFlowers.values().removeIf(value -> value == 0);
        lock.unlock();
    }

}
