import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FunctionHandler implements InvocationHandler {

    private Object fun;

    public FunctionHandler(Object function) {
        this.fun = function;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(fun, args);
    }
}