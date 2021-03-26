package exe1;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Mode
 * Mode 表示 JMH 进行 Benchmark 时所使用的模式。通常是测量的维度不同，或是测量的方式不同。目前 JMH 共有四种模式：
 * <p>
 * Throughput: 整体吞吐量，例如“1秒内可以执行多少次调用”。
 * AverageTime: 调用的平均时间，例如“每次调用平均耗时xxx毫秒”。
 * SampleTime: 随机取样，最后输出取样结果的分布，例如“99%的调用在xxx毫秒以内，99.99%的调用在xxx毫秒以内”
 * SingleShotTime: 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmup
 * 次数设为0，用于测试冷启动时的性能。
 *
 * @Mode Mode 如之前所说，表示 JMH 进行 Benchmark 时所使用的模式。
 * @State State 用于声明某个类是一个“状态”，然后接受一个 Scope 参数用来表示该状态的共享范围。
 * 因为很多 benchmark 会需要一些表示状态的类，JMH 允许你把这些类以依赖注入的方式注入到 benchmark 函数里。
 * Scope 主要分为两种。
 * <p>
 * Thread: 该状态为每个线程独享。
 * Benchmark: 该状态在所有线程间共享。
 * 关于State的用法，官方的 code sample 里有比较好的例子。
 * @OutputTimeUnit benchmark 结果所使用的时间单位。
 */
@BenchmarkMode(value = {Mode.All})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class FirstBenchmark {

    /**
     * @Benchmark 表示该方法是需要进行 benchmark 的对象，用法和 JUnit 的 @Test 类似。
     */
    @Benchmark
    public int sleepAWhile() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }
        return 0;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // benchmark 所在的类的名字，注意这里是使用正则表达式对所有类进行匹配的。
                .include(FirstBenchmark.class.getSimpleName())
                // 进行 fork 的次数。如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试。
                .forks(2)
                // 预热的迭代次数。
                .warmupIterations(5)
                // 实际测量的迭代次数。
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }
}