package exe162.find.peak.element;

class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return 0;
        }

        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return 0;
            } else {
                return 1;
            }
        }

        return doFindPeakElement(nums, 0, nums.length - 1);
    }

    private int doFindPeakElement(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        int isPeak = isPeak(nums, mid);
        if (isPeak == -1) {
            return mid;
        } else if (isPeak > mid) {
            return doFindPeakElement(nums, isPeak, end);
        } else {
            return doFindPeakElement(nums, start, isPeak);
        }
    }

    private int isPeak(int[] nums, int mid) {
        if (mid == 0) {
            if (nums[mid] > nums[mid + 1]) {
                return -1;
            } else {
                return mid + 1;
            }
        } else if (mid == nums.length - 1) {
            if (nums[mid] > nums[mid - 1]) {
                return -1;
            } else {
                return mid - 1;
            }
        } else {
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return -1;
            } else if (nums[mid] < nums[mid - 1]) {
                return mid - 1;
            } else {
                return mid + 1;
            }
        }

    }
}