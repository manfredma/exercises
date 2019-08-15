package eager;

public class ConcreateImplementorB implements Implementor {
    @Override
    public void operation() {
        System.out.println("this is concreteImplementorB's operation...");
    }
}