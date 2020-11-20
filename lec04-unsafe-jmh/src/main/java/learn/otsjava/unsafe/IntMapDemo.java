package learn.otsjava.unsafe;

import java.util.HashMap;
import java.util.Map;

public class IntMapDemo {
    public static void main(String[] args) {
        int mapSize = 2_000_000;
        String keyStr = "k";

        long start;
        long elapsed;
        long sum1 = 0;
        IntMap myMap = new IntMap(mapSize * 2);
        start = System.currentTimeMillis();
        for (int idx = 0; idx < mapSize; idx++) {
            myMap.put(keyStr + idx, idx);
        }
        for (int idx = 0; idx < mapSize; idx++) {
            sum1 += myMap.get(keyStr + idx);
        }
        elapsed = System.currentTimeMillis() - start;
        printElapsed("IntMap time", elapsed);
        System.out.println("sum1 = " + sum1);

        System.out.println("------------------------");
        long sum2 = 0;
        Map<String, Integer> hashMap = new HashMap<>(mapSize);
        start = System.currentTimeMillis();
        for (int idx = 0; idx < mapSize; idx++) {
            hashMap.put(keyStr + idx, idx);
        }
        for (int idx = 0; idx < mapSize; idx++) {
            sum2 += hashMap.get(keyStr + idx);
        }
        printElapsed("HashMap time", (System.currentTimeMillis() - start));
        System.out.println("sum2 = " + sum2);
    }

    private static void printElapsed(String name, long elapsed) {
        System.out.printf("%18s time: %d%n", name, elapsed);
    }
}
