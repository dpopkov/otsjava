package learn.otsjava.unsafe;

import java.util.ArrayList;
import java.util.List;

public class IntArrayDemo {
    public static final int INITIAL_CAPACITY = 10;
    public static final int MAXIMUM_CAPACITY = 10_000_000;

    public static void main(String[] args) throws Exception {
        long sum1 = 0;
        IntArrayPrimitive intList = new IntArrayPrimitive(INITIAL_CAPACITY);
        long start;
        long elapsed;
        start = System.currentTimeMillis();
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            intList.setValue(i, i);
        }
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            sum1 += intList.getValue(i);
        }
        elapsed = System.currentTimeMillis() - start;
        printElapsed("IntArrayPrimitive", elapsed);
        System.out.println("sum1 = " + sum1);
        System.out.println("--------------------");

        long sum2 = 0;
        try (IntArrayWithUnsafe intArrayUnsafe = new IntArrayWithUnsafe(INITIAL_CAPACITY)) {
            start = System.currentTimeMillis();
            for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
                intArrayUnsafe.setValue(i, i);
            }
            for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
                sum2 += intArrayUnsafe.getValue(i);
            }
            elapsed = System.currentTimeMillis() - start;
        }
        printElapsed("IntArrayWithUnsafe", elapsed);
        System.out.println("sum2 = " + sum2);
        System.out.println("--------------------");

        List<Integer> arrayList = new ArrayList<>(INITIAL_CAPACITY);
        long sum3 = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            arrayList.add(i, i);
        }
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            sum3 += arrayList.get(i);
        }
        elapsed = System.currentTimeMillis() - start;
        printElapsed("ArrayList", elapsed);
        System.out.println("sum3 = " + sum3);
    }

    private static void printElapsed(String name, long elapsed) {
        System.out.printf("%18s time: %d%n", name, elapsed);
    }


}
