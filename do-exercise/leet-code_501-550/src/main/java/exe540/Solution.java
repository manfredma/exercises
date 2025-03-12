package exe540;

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == nums[mid - 1] && mid % 2 == 1) {
                left = mid + 1;
            } else if (nums[mid] == nums[mid + 1] && mid % 2 == 0) {
                left = mid + 2;
            } else if (nums[mid] == nums[mid - 1] && mid % 2 == 0) {
                right = mid - 2;
            } else if (nums[mid] == nums[mid + 1] && mid % 2 == 1) {
                right = mid - 1;
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}