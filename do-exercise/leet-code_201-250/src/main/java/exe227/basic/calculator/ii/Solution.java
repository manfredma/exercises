package exe227.basic.calculator.ii;

import java.util.Stack;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 整数除法仅保留整数部分。
 * <p>
 * 你可以假设给定的表达式总是有效的。所有中间结果将在[-2^31, 2^31- 1] 的范围内。
 * <p>
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * <p>
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 */
class Solution {
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        int index = 0;
        Stack<Object> stack = new Stack<>();
        index = parseInteger(s, index, stack);

        while (index < s.length()) {
            index = parseOp(s, index, stack);
            char op = (Character) stack.peek();
            index = parseInteger(s, index, stack);
            if (op == '*' || op == '/') {
                int right = (Integer) stack.pop();
                char x = (Character) stack.pop();
                int left = (Integer) stack.pop();
                if (x == '*') {
                    stack.push(right * left);
                } else {
                    stack.push(left / right);
                }
            }
        }

        int result = (Integer) stack.get(0);
        for (int i = 1; i < stack.size(); i = i + 2) {
            if ((Character) stack.get(i) == '+') {
                result = result + (Integer) stack.get(i + 1);
            } else {
                result = result - (Integer) stack.get(i + 1);
            }
        }
        return result;
    }

    private int parseInteger(String s, int index, Stack<Object> stack) {
        StringBuilder x = new StringBuilder();
        while (index < s.length()) {
            if (!Character.isDigit(s.charAt(index))) {
                break;
            }
            x.append(s.charAt(index));
            index++;
        }

        stack.push(Integer.parseInt(x.toString()));
        return index;
    }

    private int parseOp(String s, int index, Stack<Object> stack) {
        stack.push(s.charAt(index));
        return ++index;
    }
}