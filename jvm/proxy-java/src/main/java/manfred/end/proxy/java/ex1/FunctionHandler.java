package manfred.end.proxy.java.ex1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author maxingfang
 */
public class FunctionHandler implements InvocationHandler {

    private Object fun;

    public FunctionHandler(Object function) {
        this.fun = function;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("method=%s", method.getName()));
        return method.invoke(fun, args);
    }
}