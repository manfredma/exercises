package exe01._05.one.away.lcci;

public class Main {

    public static void main(String[] args) {
        SolutionV2 solutionV2 = new SolutionV2();
        System.out.println(solutionV2.oneEditAway("pale", "ple"));
        System.out.println(solutionV2.oneEditAway("pales", "pales"));
        System.out.println(solutionV2.oneEditAway("pales", "pal"));
        System.out.println(solutionV2.oneEditAway("", "a"));

        Solution solution = new Solution();
        System.out.println(solution.oneEditAway("pale", "ple"));
        System.out.println(solution.oneEditAway("pales", "pales"));
        System.out.println(solution.oneEditAway("pales", "pal"));
        System.out.println(solution.oneEditAway("", "a"));
    }
}
