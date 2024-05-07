package exe81.search.in.rotated.sorted.array.ii;

class Solution {
    public boolean search(int[] nums, int target) {
        int m = nums.length;
        if (m == 0) {
            return false;
        }
        return doSearch(nums, target, 0, nums.length - 1);
    }

    private boolean doSearch(int[] nums, int target, int begin, int end) {
        if (end < 0 || begin > nums.length - 1) {
            return false;
        }
        if (begin > end) {
            return false;
        }
        int mid = begin + (end - begin) / 2;
        if (nums[mid] == target) {
            return true;
        }
        if (mid == begin) {
            return nums[end] == target;
        }

        if (nums[mid] > nums[begin]) {
            if (nums[mid] < target) {
                return doSearch(nums, target, mid + 1, end);
            } else {
                if (nums[begin] > target) {
                    return doSearch(nums, target, mid + 1, end);
                } else {
                    return doSearch(nums, target, begin, mid - 1);
                }
            }
        } else if (nums[mid] < nums[begin]) {
            if (nums[mid] > target) {
                return doSearch(nums, target, begin, mid - 1);
            } else {
                if (nums[end] < target) {
                    return doSearch(nums, target, begin, mid - 1);
                } else {
                    return doSearch(nums, target, mid + 1, end);
                }
            }
        } else {
            return doSearch(nums, target, begin, mid - 1) || doSearch(nums, target, mid + 1, end);
        }
    }
}