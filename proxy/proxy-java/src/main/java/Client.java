import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        IFunction fun = (IFunction) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IFunction.class}, new FunctionHandler(new FunctionImpl()));
        try {
            fun.doSomething();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}