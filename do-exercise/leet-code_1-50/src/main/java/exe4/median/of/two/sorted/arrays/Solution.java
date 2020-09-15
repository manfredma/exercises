package exe4.median.of.two.sorted.arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (((nums1.length + nums2.length) & 1) == 1) {
            int middleIndex = (nums1.length + nums2.length) / 2;
            int index = 0;
            int index1 = 0;
            int index2 = 0;
            while (true) {
                if (index1 > nums1.length - 1) {
                    if (index == middleIndex) {
                        return nums2[index2];
                    }
                    index2++;
                } else if (index2 > nums2.length - 1) {
                    if (index == middleIndex) {
                        return nums1[index1];
                    }
                    index1++;
                } else if (nums1[index1] > nums2[index2]) {
                    if (index == middleIndex) {
                        return nums2[index2];
                    }
                    index2++;
                } else {
                    if (index == middleIndex) {
                        return nums1[index1];
                    }
                    index1++;
                }
                index++;
            }
        } else {
            int middleIndexB = (nums1.length + nums2.length) / 2;
            int middleIndexS = middleIndexB - 1;
            double middleS = 0;
            int index = 0;
            int index1 = 0;
            int index2 = 0;
            while (true) {
                if (index1 > nums1.length - 1) {
                    if (index == middleIndexS) {
                        middleS = nums2[index2];
                    } else if (index == middleIndexB) {
                        return (middleS + nums2[index2]) / 2.0;
                    }
                    index2++;
                } else if (index2 > nums2.length - 1) {
                    if (index == middleIndexS) {
                        middleS = nums1[index1];
                    } else if (index == middleIndexB) {
                        return (middleS + nums1[index1]) / 2.0;
                    }
                    index1++;
                } else if (nums1[index1] > nums2[index2]) {
                    if (index == middleIndexS) {
                        middleS = nums2[index2];
                    } else if (index == middleIndexB) {
                        return (middleS + nums2[index2]) / 2.0;
                    }
                    index2++;
                } else {
                    if (index == middleIndexS) {
                        middleS = nums1[index1];
                    } else if (index == middleIndexB) {
                        return (middleS + nums1[index1]) / 2.0;
                    }
                    index1++;
                }
                index++;
            }
        }
    }
}