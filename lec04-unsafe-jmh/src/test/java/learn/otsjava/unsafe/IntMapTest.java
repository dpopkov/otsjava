package learn.otsjava.unsafe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntMapTest {

    @Test
    void whenPutThenCanGet() {
        IntMap map = new IntMap(8);
        map.put("k1", 11);
        map.put("k2", 22);
        assertEquals(11, map.get("k1"));
        assertEquals(22, map.get("k2"));
    }
}
