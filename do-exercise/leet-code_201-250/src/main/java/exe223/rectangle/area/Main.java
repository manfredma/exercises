package exe223.rectangle.area;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        System.out.println(solution.computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
        System.out.println(solution.computeArea(-2, -2, 2, 2, -1, -1, 1, 1));
    }
}
