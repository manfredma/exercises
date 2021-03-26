package cache.line;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(value = {Mode.All})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)
public class CacheLineTest {

    private long[][] arr;

    @Benchmark
    public void cacheHit() {
        long sum = 0;
        // 横向遍历
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
    }

    @Benchmark
    public void cacheMiss() {
        long sum = 0;
        // 纵向遍历
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // benchmark 所在的类的名字，注意这里是使用正则表达式对所有类进行匹配的。
                .include(CacheLineTest.class.getSimpleName())
                // 进行 fork 的次数。如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试。
                .forks(1)
                // 预热的迭代次数。
                .warmupIterations(2)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void prepare() {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
    }
}