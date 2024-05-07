package exe131.palindrome.partitioning;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || "".equals(s)) {
            return result;
        }

        doPartition(s, result, new ArrayList<>(), 0);
        return result;
    }

    private void doPartition(String s, List<List<String>> result, List<String> temp, int index) {
        if (index == s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                String subStr = s.substring(index, i + 1);
                temp.add(subStr);
                doPartition(s, result, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}