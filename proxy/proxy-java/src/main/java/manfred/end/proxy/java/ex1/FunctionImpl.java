package manfred.end.proxy.java.ex1;

/**
 * @author maxingfang
 */
public class FunctionImpl implements IFunction {
    @Override
    public void doSomething() throws IllegalStateException {
        // 方法什么也不做, 只抛异常
        throw new IllegalStateException();
    }
}