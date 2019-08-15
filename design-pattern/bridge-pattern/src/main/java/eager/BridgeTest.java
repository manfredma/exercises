package eager;

public class BridgeTest {
    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction();
        //调用第一个实现类
        abstraction.setImplementor(new ConcreateImplementorA());
        abstraction.operation();

        //调用第二个实现类
        abstraction.setImplementor(new ConcreateImplementorB());
        abstraction.operation();

    }
}