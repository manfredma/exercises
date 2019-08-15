package basic;

public class ConcreteNodeA implements Node{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
