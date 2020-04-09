package cn.eaglefire.app.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author manfred
 * @since 2020-04-08 10:43 上午
 */
public class BatchConsumer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1024);
        for (int i = 0; i < 1000000; i++) {
            executorService.submit(() -> {
                System.out.println("Begin to load");
                // 加载Spring配置文件
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
                context.start();
                //
                System.out.println("End to load");
                DemoService demoService = (DemoService)context.getBean("demoService");
                String result = demoService.sayHello("Eagle");
                System.out.println("The result is: "+result);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                context.close();
            });
        }

    }
}
