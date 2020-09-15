package exe137.single.number.ii;

class Solution {

    // https://juejin.im/post/5cb962dbf265da03761e8601
    /**
     *  如上所推
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int tempA = (~a & b & c) + (a & ~b & ~c);
            b = (~a & ~b & c) + (~a & b & ~c);
            a = tempA;
        }
        return b;
    }
    /**
     *  如上所推,你也可以写成这样
     */
    public int singleNumber2(int[] nums) {
        int a=0,b=0;
        for (int c : nums) {
            b = b ^ c & ~ a;
            a = a ^ c & ~ b;
        }
        return b;
    }

}