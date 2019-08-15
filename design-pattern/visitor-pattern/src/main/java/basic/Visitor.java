package basic;

public interface Visitor {
    //这里用到了静态分派，在编译期就能确定，ConcreteNodeA参数走visit(ConcreteNodeA concreteNodeA)方法，
    //ConcreteNodeB参数走visit(ConcreteNodeA concreteNodeB)方法。利用方法的重载
    void visit(ConcreteNodeA concreteNodeA);

    void visit(ConcreteNodeB concreteNodeB);
}