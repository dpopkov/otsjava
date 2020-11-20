package learn.otsjava.unsafe;

public class IntMap {
    private final String[] keys;
    private final int[] values;

    public IntMap(int capacity) {
        keys = new String[capacity];
        values = new int[capacity];
    }

    public void put(String key, int value) {
        int idx = baseIndex(key);
        while (keys[idx] != null) {
            idx++;
            if (idx == keys.length) {
                throw new IllegalStateException("Index exceeds map capacity");
            }
        }
        keys[idx] = key;
        values[idx] = value;
    }

    public int get(String key) {
        int idx = baseIndex(key);
        while (idx < keys.length) {
            if (key.equals(keys[idx])) {
                return values[idx];
            }
            idx++;
        }
        throw new RuntimeException("Value not found, key:" + key);
    }

    private int baseIndex(String key) {
        int h = key.hashCode() & 0x7fffffff;
        return h % keys.length;
    }
}
