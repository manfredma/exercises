package arithmetic;

/**
 * @author Manfred since 2019/8/15
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("3 + 5 - 4 + 3");
        System.out.println(calculator.calculate());
    }
}
