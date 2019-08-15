package eager;

public class ConcreateImplementorA implements Implementor {
    @Override
    public void operation() {
        System.out.println("this is concreteImplementorA's operation...");
    }
}