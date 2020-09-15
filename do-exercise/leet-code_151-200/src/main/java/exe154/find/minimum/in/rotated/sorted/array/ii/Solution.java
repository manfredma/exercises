package exe154.find.minimum.in.rotated.sorted.array.ii;

class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            // 抛出异常可能更合适一些
            return -1;
        }
        return doFindMin(nums, 0, nums.length - 1);
    }

    private int doFindMin(int[] nums, int start, int end) {
        int len = end - start + 1;
        if (len == 0) {
            return Integer.MAX_VALUE;
        }
        if (len == 1) {
            return nums[start];
        }

        if (len == 2) {
            return Math.min(nums[start], nums[start + 1]);
        }

        int midIndex = start + (end - start) / 2;
        if (nums[start] == nums[end]) {
            if (nums[midIndex] == nums[start]) {
                return Math.min(doFindMin(nums, start + 1, midIndex - 1), doFindMin(nums, midIndex, end - 1));
            } else if (nums[midIndex] > nums[start]) {
                return doFindMin(nums, midIndex + 1, end);
            } else {
                return doFindMin(nums, start + 1, midIndex);
            }
        } else if (nums[start] > nums[end]) {
            if (nums[midIndex] > nums[end]) {
                return doFindMin(nums, midIndex + 1, end);
            } else {
                return doFindMin(nums, start + 1, midIndex);
            }
        } else {
            return nums[start];
        }
    }
}