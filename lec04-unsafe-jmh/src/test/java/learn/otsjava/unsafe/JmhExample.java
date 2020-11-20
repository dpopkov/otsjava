package learn.otsjava.unsafe;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
Results:
Benchmark                      Mode  Cnt    Score   Error  Units
JmhExample.arrayListTest         ss       475,158          ms/op
JmhExample.intArrayUnsafeTest    ss        49,215          ms/op
JmhExample.intListTest           ss        72,419          ms/op
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JmhExample {
    private static final int INITIAL_CAPACITY = 10;
    private static final int MAXIMUM_CAPACITY = 10_000_000;

    private IntArrayPrimitive intList;
    private IntArrayWithUnsafe intArrayUnsafe;
    private List<Integer> arrayList;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JmhExample.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() throws Exception {
        intList = new IntArrayPrimitive(INITIAL_CAPACITY);
        intArrayUnsafe = new IntArrayWithUnsafe(INITIAL_CAPACITY);
        arrayList = new ArrayList<>(INITIAL_CAPACITY);
    }

    @Benchmark
    public long intListTest() {
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            intList.setValue(i, i);
        }
        long sum = 0;
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            sum += intList.getValue(i);
        }
        return sum;
    }

    @Benchmark
    public long intArrayUnsafeTest() {
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            intArrayUnsafe.setValue(i, i);
        }
        long sum = 0;
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            sum += intArrayUnsafe.getValue(i);
        }
        return sum;
    }

    @Benchmark
    public long arrayListTest() {
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            arrayList.add(i, i);
        }
        long sum = 0;
        for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
            sum += arrayList.get(i);
        }
        return sum;
    }

    @TearDown
    public void close() {
        intArrayUnsafe.close();
    }
}
