package exe233.number.of.digit.one;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public int countDigitOne(int n) {
        // 将每一位拆分成数组
        int[] positionArray = position(n);

        int result = 0;
        for (int i = 0; i < positionArray.length; i++) {
            // 左边的值
            int left = 0;
            for (int j = 0; j < i; j++) {
                left = left * 10 + positionArray[j];
            }
            // 右边是个全排列
            int rightP = 1;
            for (int j = i + 1; j < positionArray.length; j++) {
                rightP = rightP * 10;
            }

            result += left * rightP;
            if (positionArray[i] > 1) {
                result += rightP;
            }  else if (positionArray[i] == 1) {
                int right = 0;
                for (int j = i + 1; j < positionArray.length; j++) {
                    right = right * 10 + positionArray[j];
                }
                // 左面是固定的
                result += (right + 1);
            }

        }
        return result;

    }

    private int[] position(int n) {
        List<Integer> x = new ArrayList<>();
        while (n > 0) {
            x.add(n % 10);
            n = n / 10;
        }

        int[] result = new int[x.size()];
        for (int i = 0; i < x.size(); i++) {
            result[i] = x.get(x.size() - i - 1);
        }
        return result;
    }

}