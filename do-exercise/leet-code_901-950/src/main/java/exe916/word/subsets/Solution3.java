package exe916.word.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 * <p>
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including
 * multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 * <p>
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 * <p>
 * Return a list of all universal words in A.  You can return the words in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * Example 3:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 * Example 4:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 * Example 5:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length, B.length <= 10000
 * 1 <= A[i].length, B[i].length <= 10
 * A[i] and B[i] consist only of lowercase letters.
 * All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
class Solution3 {
    public List<String> wordSubsets(String[] A, String[] B) {

        int[][] splitB = new int[26][2];
        for (int i = 0; i < splitB.length; i++) {
            splitB[i][0] = i;
        }
        for (String singleB : B) {
            int[] tmp = new int[26];
            for (int j = 0; j < singleB.length(); j++) {
                tmp[singleB.charAt(j) - 'a']++;
            }
            for (int i = 0; i < splitB.length; i++) {
                splitB[i][1] = Math.max(splitB[i][1], tmp[i]);
            }
        }
        int index = 0;
        for (int i = 0; i < splitB.length; i++) {
            if (splitB[i][1] > 0) {
                splitB[index][0] = i;
                splitB[index][1] = splitB[i][1];
                index++;
            }
        }


        List<String> result = new ArrayList<>(A.length);
        for (String s : A) {
            char[] splitA = new char[26];
            for (int i = 0; i < s.length(); i++) {
                splitA[s.charAt(i) - 'a']++;
            }

            boolean isSubset = true;
            for (int j = 0; j < index; j++) {
                if (splitB[j][1] > splitA[splitB[j][0]]) {
                    isSubset = false;
                    break;
                }
            }
            if (isSubset) {
                result.add(s);
            }
        }
        return result;
    }
}