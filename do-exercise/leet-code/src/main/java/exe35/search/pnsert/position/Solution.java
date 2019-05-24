package exe35.search.pnsert.position;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        if (null == nums || nums.length == 0) {
            return result;
        }



        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int leftI, int rightI) {
        while (true) {
            if (leftI >= 0 && leftI <= nums.length - 1 && nums[leftI] >= target) {
                return leftI;
            }
            if (rightI >= 0 && rightI <= nums.length - 1 && nums[rightI] <= target) {

                return nums[rightI] == target? rightI:rightI + 1;
            }
            if (leftI >= rightI) {
                if (target < nums[rightI]) {
                    return rightI;
                } else {
                    return leftI;
                }
            }

            int m = leftI + (rightI - leftI) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                return search(nums, target, m + 1, rightI - 1);
            } else {
                return search(nums, target, leftI + 1, m - 1);
            }
        }
    }

}