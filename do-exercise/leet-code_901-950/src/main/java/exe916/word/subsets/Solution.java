package exe916.word.subsets;

import java.util.*;

/**
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 *
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including
 * multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 *
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 *
 * Return a list of all universal words in A.  You can return the words in any order.
 *
 * Example 1:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * Example 3:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 * Example 4:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 * Example 5:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 *
 *
 * Note:
 *
 * 1 <= A.length, B.length <= 10000
 * 1 <= A[i].length, B[i].length <= 10
 * A[i] and B[i] consist only of lowercase letters.
 * All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {

        List<char[]> splitB = new ArrayList<>(B.length);
        Set<String> existsB = new HashSet<>();
        char[] sp = new char[26];
        for (String singleB : B) {
            Arrays.fill(sp, (char)0);
            if (existsB.contains(singleB)) {
                continue;
            }
            existsB.add(singleB);
            for (int j = 0; j < singleB.length(); j++) {
                sp[singleB.charAt(j) - 'a']++;
            }
            if (existsB.add(new String(sp))) {
                splitB.add(Arrays.copyOf(sp, 26));
            }
        }

        List<String> result = new ArrayList<>(A.length);
        Map<String, Boolean> x = new HashMap<>();
        char[] splitA = new char[26];
        for (String s : A) {
            Arrays.fill(splitA, (char)0);
            for (int i = 0; i < s.length(); i++) {
                splitA[s.charAt(i) - 'a']++;
            }

            String splitAString = new String(splitA);
            if (x.containsKey(splitAString)) {
                if (x.get(splitAString)) {
                    result.add(s);
                }
                continue;
            }

            boolean isSubset = true;
            for (int i = 0; i < splitB.size(); i++) {
                if (!isSubset) {
                    break;
                }
                for (int j = 0; j < splitA.length; j++) {
                    if (splitB.get(i)[j] > splitA[j]) {
                        isSubset = false;
                        break;
                    }
                }
            }
            if (isSubset) {
                x.put(splitAString, true);
                result.add(s);
            } else {
                x.put(splitAString, false);
            }
        }
        return result;
    }
}