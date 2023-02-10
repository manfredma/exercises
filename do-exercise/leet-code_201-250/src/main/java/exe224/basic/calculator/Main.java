package exe224.basic.calculator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> expression = Arrays.asList(
                "1 + 1",
                " 2-(1 + 2) ",
                "(1+(4+5+2)-3)+(6+8)",
                "0",
                "(0)",
                "(1+2+3-(4-3))",
                "1-(-2)"
        );
        Solution solution = new Solution();
        for (String s : expression) {
            System.out.println(s + " = " + solution.calculate(s));
        }
    }
}
