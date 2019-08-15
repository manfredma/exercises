package basic;

public class Test {
    public static void main(String[] args) {

        ObjectStructure objectStructure = new ObjectStructure();
        ConcreteNodeA concreteNodeA = new ConcreteNodeA();
        ConcreteNodeB concreteNodeB = new ConcreteNodeB();
        objectStructure.add(concreteNodeA);
        objectStructure.add(concreteNodeB);
        System.out.println("------------------访问者A访问元素------------------");
        objectStructure.action(new ConcreteVisitorA());
        System.out.println("------------------访问者B访问元素------------------");
        objectStructure.action(new ConcreteVisitorB());
    }
}
