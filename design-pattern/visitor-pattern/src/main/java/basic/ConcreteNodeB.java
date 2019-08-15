package basic;

public class ConcreteNodeB implements Node{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }
}