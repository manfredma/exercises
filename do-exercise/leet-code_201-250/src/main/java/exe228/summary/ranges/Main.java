package exe228.summary.ranges;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        System.out.println(solution.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
        System.out.println(solution.summaryRanges(new int[]{}));
        System.out.println(solution.summaryRanges(new int[]{-1}));
        System.out.println(solution.summaryRanges(new int[]{0}));

        SolutionV2 solutionV2 = new SolutionV2();
        System.out.println(solutionV2.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        System.out.println(solutionV2.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
        System.out.println(solutionV2.summaryRanges(new int[]{}));
        System.out.println(solutionV2.summaryRanges(new int[]{-1}));
        System.out.println(solutionV2.summaryRanges(new int[]{0}));
    }
}
