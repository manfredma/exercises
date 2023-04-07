package manfred.end.generic.basic;

/**
 *
 */
public class JavaGenericClass<T> {

    private T a;

    public JavaGenericClass(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public <M> M convert(M m) {
        return m;
    }

    public static void main(String[] args) {
        System.out.println("hello, world");
    }


}
