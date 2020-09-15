package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.httpclient.HttpClient;
import com.linkedin.parseq.promise.Promises;
import com.ning.http.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

public class Main3 {
    public static void main(String[] args) throws IOException {

        ExecutorService poolExecutor =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("mall-async-engine-thread-%d")
                                .build(), new AsyncCallerRunsPolicy());

        Engine engine = new EngineBuilder().setTaskExecutor(poolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();

        engine.run(createTask());
        engine.shutdown();
        System.in.read();
        System.exit(0);
    }

    private static Task<String> createTask() {
        final Task<String> google = fetchBody("http://www.baidu.com");
        final Task<String> yahoo = fetchBody("http://www.sina.com");
        final Task<String> bing = fetchBody("https://cn.bing.com/");

        final Task<String> plan = Task.par(google, yahoo, bing)
                .map((g, y, b) -> "baidu Page: " + g +" \n" +
                        "sina Page: " + y + "\n" +
                        "bing Page: " + b + "\n")
                .andThen(System.out::println);
        return plan;

    }

    private static Task<String> fetchBody(String url) {
        return HttpClient.get(url).task().map(Response::getResponseBody);
    }

    public static class AsyncCallerRunsPolicy implements RejectedExecutionHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCallerRunsPolicy.class);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("异步执行线程池任务执行已经被打满，请检查当前队列任务数据线程数据情况r={}, executor={}", r.toString(), executor.toString());
            throw new RuntimeException("访问页面失败，请重试");
        }

    }
}
