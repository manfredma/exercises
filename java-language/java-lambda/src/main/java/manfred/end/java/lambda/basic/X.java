package manfred.end.java.lambda.basic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class X {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    private static String now() {
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        execParallel();
        execParallelStream();
        execSerialStream();
        execCompletableFutureParallel();
    }

    private static void execSerialStream() {
        System.out.println("\n" + now() + " 串行流执行示例：");

        List<String> items = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Grapes",
                "Pear", "Peach", "Melon", "Watermelon", "Pineapple");

        System.out.println(now() + " 开始处理集合元素...");

        items.stream()
                .map(item -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(now() + " 线程 " + threadName + " 正在处理: " + item);
                    // 模拟处理时间
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return item.toUpperCase();
                })
                .forEach(result -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(now() + " 线程 " + threadName + " 输出结果: " + result);
                });

        System.out.println(now() + " 串行流处理完成");
    }

    private static void execCompletableFutureParallel() {
        System.out.println("\n" + now() + " CompletableFuture并行执行示例：");

        List<String> items = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Grapes",
                "Pear", "Peach", "Melon", "Watermelon", "Pineapple");

        System.out.println(now() + " 开始处理集合元素...");

        // 创建线程池
        java.util.concurrent.ExecutorService executor =
                java.util.concurrent.Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            items.stream()
                    .map(item -> java.util.concurrent.CompletableFuture.supplyAsync(() -> {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(now() + " 线程 " + threadName + " 正在处理: " + item);
                        // 模拟处理时间
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return item.toUpperCase();
                    }, executor))
                    .collect(Collectors.toList())
                    .stream()
                    .map(CompletableFuture::join)
                    .forEach(result -> {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(now() + " 线程 " + threadName + " 输出结果: " + result);
                    });
        } finally {
            // 关闭线程池
            executor.shutdown();
        }

        System.out.println(now() + " CompletableFuture处理完成");

    }

    private static void execParallelStream() {
        System.out.println("\n" + now() + " parallelStream执行示例：");

        List<String> items = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Grapes",
                "Pear", "Peach", "Melon", "Watermelon", "Pineapple");

        System.out.println(now() + " 开始处理集合元素...");

        items.parallelStream()
                .map(item -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(now() + " 线程 " + threadName + " 正在处理: " + item);
                    // 模拟处理时间
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return item.toUpperCase();
                })
                .forEach(result -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(now() + " 线程 " + threadName + " 输出结果: " + result);
                });

        System.out.println(now() + " parallelStream处理完成");


    }

    private static void execParallel() {
        // 创建一个并行流并执行操作
        System.out.println(now() + " 并行流执行示例：");

        // 使用并行流处理一个大数组
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        // 使用并行流并打印线程信息
        Arrays.stream(numbers)
                .parallel()
                .map(n -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(now() + " 处理元素 " + n + " 的线程: " + threadName);
                    // 模拟耗时操作
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return n * 2;
                })
                .forEach(n -> System.out.println(now() + " 结果: " + n));

        // 测试并行流与串行流的性能差异
        System.out.println("\n" + now() + " 性能对比测试：");

        // 创建一个较大的集合
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            largeList.add(i);
        }

        // 测试串行流性能
        long startSerial = System.currentTimeMillis();
        long serialSum = largeList.stream()
                .mapToLong(i -> i)
                .sum();
        long endSerial = System.currentTimeMillis();
        System.out.println(now() + " 串行流耗时: " + (endSerial - startSerial) + "ms, 结果: " + serialSum);

        // 测试并行流性能
        long startParallel = System.currentTimeMillis();
        long parallelSum = largeList.stream()
                .parallel()
                .mapToLong(i -> i)
                .sum();
        long endParallel = System.currentTimeMillis();
        System.out.println(now() + " 并行流耗时: " + (endParallel - startParallel) + "ms, 结果: " + parallelSum);
    }
}
