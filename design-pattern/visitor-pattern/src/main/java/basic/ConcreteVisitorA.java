package basic;

public class ConcreteVisitorA implements Visitor{
    @Override
    public void visit(ConcreteNodeA concreteNodeA) {


        //具体处理过程写这里面
        System.out.println("ConcreteVisitorA 处理 concreteNodeA");
    }

    @Override
    public void visit(ConcreteNodeB concreteNodeB) {
        //具体处理过程写这里面
        System.out.println("ConcreteVisitorA 处理 concreteNodeB");
    }
}