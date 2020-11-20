package learn.otsjava.unsafe;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JmhMapExample {
    private static final int MAP_SIZE = 200_000;
    private static final String KEY_STR = "k";

    private IntMap intMap;
    private Map<String, Integer> hashMap;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JmhMapExample.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        intMap = new IntMap(MAP_SIZE * 2);
        hashMap = new HashMap<>();
    }

    @Benchmark
    public long intMapTest() {
        long sum = 0;
        for (int idx = 0; idx < MAP_SIZE; idx++) {
            intMap.put(KEY_STR + idx, idx);
        }
        for (int idx = 0; idx < MAP_SIZE; idx++) {
            sum += intMap.get(KEY_STR + idx);
        }
        return sum;
    }

    @Benchmark
    public long hashMapTest() {
        long sum = 0;
        for (int idx = 0; idx < MAP_SIZE; idx++) {
            hashMap.put(KEY_STR + idx, idx);
        }
        for (int idx = 0; idx < MAP_SIZE; idx++) {
            sum += hashMap.get(KEY_STR + idx);
        }
        return sum;
    }
}
