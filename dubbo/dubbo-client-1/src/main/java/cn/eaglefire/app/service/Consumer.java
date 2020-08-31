package cn.eaglefire.app.service;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Future;

public class Consumer {
    /**
     * 主方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //
        System.out.println("Begin to load");
        // 加载Spring配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        context.start();
        //
        System.out.println("End to load");

        // 调用远程方法
        DemoService demoService = (DemoService) context.getBean("demoService");
        String result = demoService.sayHello("Eagle");
        Future<String> resultFuture = RpcContext.getContext().getFuture();
        System.out.println("The result is: " + resultFuture.get());

        // 为保证服务一直开着，利用输入流的阻塞来模拟
        System.in.read();
    }
}