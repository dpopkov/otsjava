package learn.otsjava.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

public class IntArrayWithUnsafe implements AutoCloseable {
    private final Unsafe unsafe;
    private final long elementSizeBytes;
    private long arrayBeginAddress;
    private int capacity;

    public IntArrayWithUnsafe(int initialCapacity) throws Exception {
        capacity = initialCapacity;

        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        unsafe = unsafeConstructor.newInstance();

        elementSizeBytes = Integer.SIZE / 8;
        arrayBeginAddress = unsafe.allocateMemory(capacity * elementSizeBytes);
    }

    public int getValue(int index) {
        return unsafe.getInt(index(index));
    }

    public void setValue(int index, int value) {
        if (index >= capacity) {
            capacity *= 2;
            arrayBeginAddress = unsafe.reallocateMemory(arrayBeginAddress, capacity * elementSizeBytes);
        }
        unsafe.putInt(index(index), value);
    }

    private long index(int offset) {
        return arrayBeginAddress + offset * elementSizeBytes;
    }

    @Override
    public void close() {
        unsafe.freeMemory(arrayBeginAddress);
    }
}
