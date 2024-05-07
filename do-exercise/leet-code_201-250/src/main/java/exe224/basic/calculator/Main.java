package exe224.basic.calculator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TestEntry> expression = Arrays.asList(
                new TestEntry("-1+2", 1),
                new TestEntry("1-(-2)", 3),
                new TestEntry("-1", -1),
                new TestEntry("-(1)", -1),
                new TestEntry("3+-4", -1),
                new TestEntry("1 + 1", 2),
                new TestEntry(" 2-(1 + 2) ", -1),
                new TestEntry("(1+(4+5+2)-3)+(6+8)", 23),
                new TestEntry("0", 0),
                new TestEntry("(0)", 0),
                new TestEntry("(1+2+3-(4-3))", 5)
        );

        Solution solution = new Solution();
        for (TestEntry s : expression) {
            System.out.print(s + " = ");
            if (solution.calculate(s.expression) == s.result) {
                System.out.println(solution.calculate(s.expression) + " : " + s.result);
            } else {
                System.out.println();
                System.err.println(s + " = " + solution.calculate(s.expression) + " : expect " + s.result);
            }

        }
    }

    static class TestEntry {

        String expression;

        int result;

        TestEntry(String expression, int result) {
            this.expression = expression;
            this.result = result;
        }

        public String toString() {
            return String.format("{expression:'%s', result: %d}", expression, result);
        }
    }
}
