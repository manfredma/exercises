package exe916.word.subsets;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"e", "o"})
        );

        System.out.println(solution.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"l", "e"})
        );
        System.out.println(solution.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"e", "oo"})
        );

        System.out.println(solution.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"lo", "eo"})
        );
        System.out.println(solution.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"ec", "oc", "ceo"})
        );

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"e", "o"})
        );

        System.out.println(solution2.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"l", "e"})
        );
        System.out.println(solution2.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"e", "oo"})
        );

        System.out.println(solution2.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"lo", "eo"})
        );
        System.out.println(solution2.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"ec", "oc", "ceo"})
        );

        Solution3 solution3 = new Solution3();
        System.out.println(solution3.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"e", "o"})
        );

        System.out.println(solution3.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"l", "e"})
        );
        System.out.println(solution3.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"e", "oo"})
        );

        System.out.println(solution3.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"lo", "eo"})
        );
        System.out.println(solution3.wordSubsets(
                new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                new String[]{"ec", "oc", "ceo"})
        );
    }
}
