package learn.otsjava.unsafe;

import java.util.Arrays;

public class IntArrayPrimitive {
    private int[] values;
    private int size;

    public IntArrayPrimitive(int initialCapacity) {
        values = new int[initialCapacity];
    }

    public int getValue(int index) {
        return values[index];
    }

    public void setValue(int index, int value) {
        if (index == values.length) {
            values = Arrays.copyOf(values, values.length * 2);
        }
        values[size++] = value;
    }
}
