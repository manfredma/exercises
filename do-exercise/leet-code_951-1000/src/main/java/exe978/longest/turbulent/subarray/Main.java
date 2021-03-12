package exe978.longest.turbulent.subarray;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println(solution.maxTurbulenceSize(new int[]{4, 8, 12, 16}));
        System.out.println(solution.maxTurbulenceSize(new int[]{100}));
    }
}
