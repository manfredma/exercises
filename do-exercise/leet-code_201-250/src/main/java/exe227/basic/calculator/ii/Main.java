package exe227.basic.calculator.ii;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("42"));
        System.out.println(solution.calculate("3+2*2"));
        System.out.println(solution.calculate("3/2"));
        System.out.println(solution.calculate(" 3+5 / 2 "));

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.calculate("42"));
        System.out.println(solution2.calculate("3+2*2"));
        System.out.println(solution2.calculate("3/2"));
        System.out.println(solution2.calculate(" 3+5 / 2 "));
    }
}
