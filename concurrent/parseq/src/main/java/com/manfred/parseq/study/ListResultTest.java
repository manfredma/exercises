package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.promise.Promise;
import com.linkedin.parseq.promise.SettablePromise;
import com.linkedin.parseq.trace.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.slf4j.LoggerFactory.getLogger;

public class ListResultTest {

    private static List<Task> taskList = new ArrayList<>();

    private static final Logger LOGGER = getLogger(ListResultTest.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService poolExecutor =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("mall-async-engine-thread-%d")
                                .build(), new AsyncCallerRunsPolicy());

        Engine engine = new EngineBuilder().setTaskExecutor(poolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();
        Task task = createCompoundTaskV4();
        engine.run(task, "xxx");
        task.await();

        LOGGER.info(TraceUtil.getJsonTrace(task));
        engine.shutdown();
        System.exit(0);
    }

    private static void listTaskResult() {
        for (int i = taskList.size() - 1; i >= 0; i--) {
            Task task = taskList.get(i);
            System.err.println(task.getName() + " : \t" + task.getClass().getName() + ",  \t" + (task instanceof SettablePromise) + " - " + (task instanceof Promise));
        }
        System.err.println("listTaskResult begin");
        for (int i = taskList.size() - 1; i >= 0; i--) {
            Task task = taskList.get(i);
            System.err.println(">>> begin " + task.getName() + "， class=" + task.getClass().getName());
            try {
                System.err.println(task.getName() + ":" + task.get());
            } catch (Exception e) {
                System.err.println(task.getName() + ":+++++++++++++++++++++++++++++++++");
                e.printStackTrace();
                System.err.println(task.getName() + ":+++++++++++++++++++++++++++++++++");
            }
            System.err.println("<<< end " + task.getName());
        }
        System.err.println("listTaskResult end");

        taskList.get(taskList.size() - 1).getTrace().getTraceMap().forEach(
                (k, v) -> {
                    System.err.println(k);
                    System.err.println(v.getValue() + " : " + v.toString());
                }
        );
    }

    private static Task<Object> createTask(int id) {
        Task<Object> task = Task.callable("TaskSuccess " + id, () -> "S " + id);
        taskList.add(task);
        return task;
    }

    private static Task<Object> createErrorTask(int id) {
        Task<Object> task = Task.callable("TaskError" + id,
                () -> {
                    throw new RuntimeException("simu");
                }
        );
        taskList.add(task);
        return task;
    }

    private static Task createCompoundTaskV4() {
        Task task0 = createTask(1);
        Task task1 = createErrorTask(2);
        Task task2 = Task.par(task0, task1);
        Task task3 = Task.par(task0.shareable(), createTask(2), task2);
        taskList.add(task0);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        return task3;

    }

    public static class AsyncCallerRunsPolicy implements RejectedExecutionHandler {

        private static final Logger LOGGER = getLogger(AsyncCallerRunsPolicy.class);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("异步执行线程池任务执行已经被打满，请检查当前队列任务数据线程数据情况r={}, executor={}", r.toString(), executor.toString());
            throw new RuntimeException("访问页面失败，请重试");
        }

    }
}
