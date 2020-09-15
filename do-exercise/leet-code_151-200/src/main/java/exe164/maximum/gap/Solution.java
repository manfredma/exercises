package exe164.maximum.gap;

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Integer.min(min, num);
            max = Integer.max(max, num);
        }

        if (max == min + 1) {
            return 1;
        }

        int len = nums.length;
        if (len == 2) {
            return max - min;
        }

        int distance = max - min;
        if (distance < len) {
            int[] d = new int[distance + 1];
            for (int num : nums) {
                d[num - min] = num;
            }
            int r = 0;
            int cur = 0;
            for (int i = 0; i < d.length; i++) {
                if (d[i] != 0) {
                    r = Integer.max(r, i - cur);
                    cur = i;
                }
            }
            return r;
        } else {
            Bucket[] b = new Bucket[len];
            for (int i = 0; i < len; i++) {
                int num = nums[i];
                int index = (int) Math.round(1.0 * (num - min) * (len - 1) / distance);
                if (b[index] == null) {
                    b[index] = new Bucket(num);
                } else {
                    b[index].add(num);
                }
            }

            int r = 0;
            int lM = min;
            for (Bucket bucket : b) {
                if (null != bucket) {
                    int t = bucket.min - lM;
                    r = Integer.max(t, r);
                    lM = bucket.max;
                }
            }
            return r;
        }
    }

    private static class Bucket {
        int min;
        int max;

        public Bucket(int o) {
            this.min = o;
            this.max = o;
        }

        void add(int o) {
            if (o > max) {
                this.max = o;
            } else if (o < min) {
                this.min = o;
            }
        }
    }
}