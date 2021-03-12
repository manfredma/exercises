package exe978.longest.turbulent.subarray;

/**
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 * <p>
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in
 * the subarray.
 * <p>
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if
 * and only if:
 * <p>
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * Example 2:
 * <p>
 * Input: arr = [4,8,12,16]
 * Output: 2
 * Example 3:
 * <p>
 * Input: arr = [100]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 4 * 104
 * 0 <= arr[i] <= 109
 */
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return 1;
        }


        int pre = Integer.compare(arr[0], arr[1]);
        int tmpMax = pre == 0 ? 1 : 2;
        int max = tmpMax;
        for (int i = 1; i < arr.length - 1; i++) {
            int compare = Integer.compare(arr[i], arr[i + 1]);
            if (compare != 0) {
                if (pre == 0) {
                    tmpMax += 1;
                } else if (pre == compare) {
                    if (tmpMax > max) {
                        max = tmpMax;
                    }
                    tmpMax = 2;
                } else {
                    tmpMax += 1;
                }
            } else {
                // 如果当前值和下一值相等
                if (tmpMax > max) {
                    max = tmpMax;
                }
                tmpMax = 1;
            }
            pre = compare;
        }
        if (tmpMax > max) {
            max = tmpMax;
        }
        return max;
    }
}