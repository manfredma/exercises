package exe12.integer.to.roman;

class Solution {
    public String intToRoman(int num) {
        String result = "";
        int left = num;

        // 处理 > 1000 部分
        while (left >= 1000) {
            result = result + "M";
            left -= 1000;
        }

        // 处理 >= 400 部分;
        if (left >= 900) {
            result = result + "CM";
            left -= 900;
        } else if (left >= 400 && left < 500) {
            result += "CD";
            left -= 400;
        } else if (left >= 500) {
            result += "D";
            left -= 500;
        }

        // 处理 > 100 部分
        while (left >= 100) {
            result = result + "C";
            left -= 100;
        }

        // 处理 > 40 部分
        if (left >= 90) {
            result = result + "XC";
            left -= 90;
        } else if (left >= 40 && left < 50) {
            result += "XL";
            left -= 40;
        } else if (left >= 50) {
            result += "L";
            left -= 50;
        }


        // 处理 > 10 部分
        while (left >= 10) {
            result = result + "X";
            left -= 10;
        }

        // 处理 > 4 部分
        if (left == 9) {
            result = result + "IX";
            left -= 9;
        } else if (left == 4) {
            result += "IV";
            left -= 4;
        } else if (left >= 5) {
            result += "V";
            left -= 5;
        }

        // 处理 > 1 部分
        while (left > 0) {
            result = result + "I";
            left -= 1;
        }

        return result;
    }
}