package arithmetic;

public class AdditionExpression extends OperatorExpression {
    public AdditionExpression(ArithmeticExpression arithmeticExpression1,ArithmeticExpression arithmeticExpression2) {
        super(arithmeticExpression1, arithmeticExpression2);
    }
    
    @Override
    public int interptet() {
        return mArithmeticExpression1.interptet() + mArithmeticExpression2.interptet();
    }
}