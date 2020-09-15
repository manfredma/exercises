package exe150.evaluate.reverse.polish.notation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    private List<String> operatorStr = new ArrayList<>();

    {
        operatorStr.add("+");
        operatorStr.add("-");
        operatorStr.add("*");
        operatorStr.add("/");
    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String curToken = tokens[tokens.length - 1 - i];
            if (operatorStr.contains(curToken)) {
                stack.push(curToken);
            } else if (operatorStr.contains(stack.peek())) {
                stack.push(curToken);
            } else {
                while (!stack.isEmpty()) {
                    if (!operatorStr.contains(stack.peek())) {
                        curToken = operator(stack, curToken);
                    } else {
                        break;
                    }
                }
                stack.push(curToken);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private String operator(Stack<String> stack, String curToken) {
        int result;
        String operator2 = stack.pop();
        String operator = stack.pop();
        if (operator.equals("+")) {
            result = Integer.parseInt(curToken) + Integer.parseInt(operator2);
        } else if (operator.equals("-")) {
            result = Integer.parseInt(curToken) - Integer.parseInt(operator2);
        } else if (operator.equals("*")) {
            result = Integer.parseInt(curToken) * Integer.parseInt(operator2);
        } else {
            result = Integer.parseInt(curToken) / Integer.parseInt(operator2);
        }
        return String.valueOf(result);
    }
}