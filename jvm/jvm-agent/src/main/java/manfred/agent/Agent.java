package manfred.agent;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation inst) {
        new Throwable().printStackTrace(System.out);
        System.out.println("Hi, This is a agent!");
        inst.addTransformer(new TestTransformer()); //将类转换器添加到此`agent`的`instrumentation`实例之中
    }
}
