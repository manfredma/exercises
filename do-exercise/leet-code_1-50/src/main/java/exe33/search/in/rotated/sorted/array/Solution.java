package exe33.search.in.rotated.sorted.array;

class Solution {
    public int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }

        int leftI = 0;
        int rightI = nums.length - 1;
        while (true) {
            if (leftI <= nums.length - 1 && nums[leftI] == target) {
                return leftI;
            }
            if (rightI >= 0 && nums[rightI] == target) {
                return rightI;
            }
            if (leftI >= rightI) {
                return -1;
            }
            if (nums[leftI] < nums[rightI]) {
                return searchInOrder(nums, target, leftI + 1, rightI + 1);
            }

            int m = leftI + (rightI - leftI) / 2;
            if (target == nums[m]) {
                return m;
            } else if (target > nums[m]) {
                if (target > nums[leftI] && nums[leftI] > nums[m]) {
                    leftI++;
                    rightI = m - 1;
                } else {
                    leftI = m + 1;
                    rightI--;
                }
            } else {
                if (target < nums[leftI] && nums[leftI] < nums[m]) {
                    leftI = m + 1;
                    rightI--;
                } else {
                    leftI++;
                    rightI = m - 1;
                }
            }
        }
    }

    private int searchInOrder(int[] nums, int target, int leftI, int rightI) {
        while (true) {
            if (leftI >= 0 && leftI <= nums.length - 1 && nums[leftI] == target) {
                return leftI;
            } else if (rightI >= 0 && rightI <= nums.length - 1 && nums[rightI] == target) {
                return rightI;
            }
            if (leftI >= rightI) {
                return -1;
            }

            int m = leftI + (rightI - leftI) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (target > nums[m]) {
                return searchInOrder(nums, target, m + 1, rightI - 1);
            } else {
                return searchInOrder(nums, target, leftI + 1, m - 1);
            }
        }
    }
}