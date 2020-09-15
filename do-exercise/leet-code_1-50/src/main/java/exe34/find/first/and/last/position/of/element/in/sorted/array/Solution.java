package exe34.find.first.and.last.position.of.element.in.sorted.array;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = searchMin(nums, target, 0, nums.length - 1, -1);
        if (l == -1) {
            return new int[]{-1, -1};
        }
        int r = searchMax(nums, target, l, nums.length - 1, l);
        return new int[]{l, r};
    }

    private int searchMin(int[] nums, int target, int leftI, int rightI, int s) {
        while (true) {
            if (leftI >= 0 && leftI <= nums.length - 1 && nums[leftI] == target) {
                return leftI;
            }
            if (rightI >= 0 && rightI <= nums.length - 1 && nums[rightI] == target) {
                if (s == -1 || rightI < s) {
                    s = rightI;
                }
            }
            if (leftI >= rightI) {
                return s;
            }

            int m = leftI + (rightI - leftI) / 2;
            if (nums[m] == target) {
                return searchMin(nums, target, leftI + 1, m - 1, m);
            } else if (nums[m] < target) {
                return searchMin(nums, target, m + 1, rightI - 1, s);
            } else {
                return searchMin(nums, target, leftI + 1, m - 1, s);
            }
        }
    }

    private int searchMax(int[] nums, int target, int leftI, int rightI, int s) {
        while (true) {
            if (rightI >= 0 && rightI <= nums.length - 1 && nums[rightI] == target) {
                return rightI;
            }
            if (leftI >= 0 && leftI <= nums.length - 1 && nums[leftI] == target) {
                if (leftI > s) {
                    s = leftI;
                }
            }
            if (leftI >= rightI) {
                return s;
            }

            int m = leftI + (rightI - leftI) / 2;
            if (nums[m] == target) {
                return searchMax(nums, target, m + 1, rightI - 1, m);
            } else if (nums[m] < target) {
                return searchMax(nums, target, m + 1, rightI - 1, s);
            } else {
                return searchMax(nums, target, leftI + 1, m - 1, s);
            }
        }
    }
}