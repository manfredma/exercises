package manfred.end.generic.bound;

abstract class ClassBound {
    public abstract void test1();
}

interface InterfaceBound1 {
    void test2();
}

interface InterfaceBound2 {
    void test3();
}

class ParentClass<T extends ClassBound & InterfaceBound1 & InterfaceBound2> {
    private final T item;

    public ParentClass(T item) {
        this.item = item;
    }

    public void test1() {
        item.test1();
    }

    public void test2() {
        item.test2();
    }

    public void test3() {
        item.test3();
    }
}

class SubClass extends ClassBound implements InterfaceBound1, InterfaceBound2 {

    @Override
    public void test1() {
        System.out.println("test1");
    }

    @Override
    public void test2() {
        System.out.println("test2");
    }

    @Override
    public void test3() {
        System.out.println("test3");
    }
}

public class Bound {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        ParentClass<SubClass> parentClass = new ParentClass<>(subClass);
        parentClass.test1();
        parentClass.test2();
        parentClass.test3();
    }
}