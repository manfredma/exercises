package exe218.the.skyline.problem;

public class Main {
    public static void main(String[] args) {
        int[][] input = {
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        };
        Solution solution = new Solution();
        System.out.println(solution.getSkyline(input));

        SolutionV2 solutionV2 = new SolutionV2();
        System.out.println(solutionV2.getSkyline(input));
    }
}
