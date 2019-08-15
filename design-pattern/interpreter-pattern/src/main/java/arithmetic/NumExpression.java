package arithmetic;

/** 对数字进行解释 */
public class NumExpression extends ArithmeticExpression {
    private int num;
        
    public NumExpression(int num) {
        this.num = num;
    }
        
    @Override 
    public int interptet() {
        return num;
    }
}

