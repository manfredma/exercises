package exe224.basic.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3* 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1"和 "+(2 + 3)"无效)
 * '-' 可以用作一元运算(即 "-1"和 "-(2 + 3)"是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 * <p>
 */
class Solution {
    public int calculate(String s) {
        AstNode x = parse(s);
        return calculate(x);
    }

    private int calculate(AstNode astNode) {
        if (astNode.token.type == Token.Type.ADD) {
            return calculate(astNode.child1) + calculate(astNode.child2);
        } else if (astNode.token.type == Token.Type.SUB) {
            return calculate(astNode.child1) - calculate(astNode.child2);
        } else if (astNode.token.type == Token.Type.NEGATIVE) {
            return -calculate(astNode.child1);
        } else {
            return Integer.parseInt(astNode.token.text);
        }
    }

    public AstNode parse(String s) {
        List<Token> tokens = tokenize(s);
        return prog(tokens);
    }

    private AstNode prog(List<Token> tokens) {

        Stack<Token> opStack = new Stack<>();
        List<Token> rpnTokens = rpnExpression(tokens, opStack);
        AstNode head = new AstNode(rpnTokens.get(rpnTokens.size() - 1));
        AstNode needChildrenAstNode = head;
        for (int i = rpnTokens.size() - 2; i >= 0; i--) {
            Token token = rpnTokens.get(i);
            AstNode astNode = new AstNode(token);
            while (needChildrenAstNode != null) {
                if (needChildrenAstNode.token.type == Token.Type.NEGATIVE) {
                    if (needChildrenAstNode.child1 == null) {
                        needChildrenAstNode.child1 = astNode;
                        astNode.parent = needChildrenAstNode;
                        break;
                    }
                } else {
                    if (needChildrenAstNode.child2 == null) {
                        needChildrenAstNode.child2 = astNode;
                        astNode.parent = needChildrenAstNode;
                        break;
                    }
                    if (needChildrenAstNode.child1 == null) {
                        needChildrenAstNode.child1 = astNode;
                        astNode.parent = needChildrenAstNode;
                        break;
                    }
                }
                needChildrenAstNode = needChildrenAstNode.parent;
            }
            if (token.type == Token.Type.ADD || token.type == Token.Type.SUB || token.type == Token.Type.NEGATIVE) {
                needChildrenAstNode = astNode;
            }
        }
        return head;
    }

    private List<Token> rpnExpression(List<Token> tokens, Stack<Token> opStack) {
        // 先转换为逆波兰表达式（算法：调度场算法 https://zh.wikipedia.org/wiki/%E8%B0%83%E5%BA%A6%E5%9C%BA%E7%AE%97%E6%B3%95）
        List<Token> rpnTokens = new ArrayList<>();
        for (Token token : tokens) {
            if (token.type == Token.Type.ADD
                    || token.type == Token.Type.SUB
                    || token.type == Token.Type.NEGATIVE) {
                // 如果这个记号表示一个操作符，记做o1，那么：
                //     只要存在另一个记为o2的操作符位于栈的顶端，并且
                //            如果o1是左结合性的并且它的运算符优先级要小于或者等于o2的优先级，或者
                //            如果o1是右结合性的并且它的运算符优先级比o2的要低，那么
                //                将o2从栈的顶端弹出并且放入输出队列中（循环直至以上条件不满足为止）；
                //     然后，将o1压入栈的顶端。
                while (!opStack.empty()) {
                    Token o2 = opStack.peek();
                    // 右结合性的
                    if (token.type == Token.Type.NEGATIVE) {
                        break;
                    } else {
                        if (o2.type == Token.Type.ADD
                                || o2.type == Token.Type.SUB
                                || o2.type == Token.Type.NEGATIVE) {
                            rpnTokens.add(opStack.pop());
                        } else {
                            break;
                        }
                    }
                }
                opStack.push(token);
            } else if (token.type.equals(Token.Type.LP)) {
                opStack.push(token);
            } else if (token.type.equals(Token.Type.RP)) {
                // 如果这个记号是一个右括号，那么：
                //     从栈当中不断地弹出操作符并且放入输出队列中，直到栈顶部的元素为左括号为止。
                //     将左括号从栈的顶端弹出，但并不放入输出队列中去。
                //     如果此时位于栈顶端的记号表示一个函数，那么将其弹出并放入输出队列中去。
                //     如果在找到一个左括号之前栈就已经弹出了所有元素，那么就表示在表达式中存在不匹配的括号。
                while (!opStack.empty()) {
                    Token o2 = opStack.peek();
                    if (o2.type.equals(Token.Type.LP)) {
                        opStack.pop();
                        break;
                    }
                    rpnTokens.add(opStack.pop());
                }
            } else {
                rpnTokens.add(token);
            }
        }
        while (!opStack.empty()) {
            rpnTokens.add(opStack.pop());
        }
        return rpnTokens;
    }

    private List<Token> tokenize(String s) {
        s = s.replace(" ", "");
        List<Token> tokens = new ArrayList<>();
        StringBuilder tokenText = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '+'
                    || currentChar == '-'
                    || currentChar == ')'
                    || currentChar == '(') {
                if (tokenText.length() > 0) {
                    tokens.add(Token.parseAndGuessType(tokenText.toString()));
                    tokenText = new StringBuilder();
                }
                tokenText.append(currentChar);
            } else {
                if (tokenText.length() > 0 && !Character.isDigit(tokenText.charAt(0))) {
                    tokens.add(Token.parseAndGuessType(tokenText.toString()));
                    tokenText = new StringBuilder();
                }
                tokenText.append(currentChar);
            }
        }
        if (tokenText.length() > 0) {
            Token token = Token.parseAndGuessType(tokenText.toString());
            if (token.text.charAt(token.text.length() - 1) == Token.Type.RP.symbol) {
                token.type = Token.Type.RP;
            } else {
                token.type = Token.Type.NUM;
            }
            tokens.add(token);
        }

        // 正确识别其中的 负号
        if (tokens.get(0).type == Token.Type.SUB) {
            tokens.get(0).type = Token.Type.NEGATIVE;
        }
        for (int i = 1; i < tokens.size(); i++) {
            if (tokens.get(i).type == Token.Type.SUB) {
                if (tokens.get(i - 1).type != Token.Type.NUM &&
                        tokens.get(i - 1).type != Token.Type.RP) {
                    tokens.get(i).type = Token.Type.NEGATIVE;
                }
            }
        }
        return tokens;
    }

    static class Token {
        String text;

        Type type;

        enum Type {
            NUM('N'), LP('('), RP(')'), ADD('+'), SUB('-'), NEGATIVE('-');
            final char symbol;

            Type(char symbol) {
                this.symbol = symbol;
            }
        }

        static Token parseAndGuessType(String text) {

            Token token = new Token();
            token.text = text;
            if (text.charAt(0) == Type.LP.symbol) {
                token.type = Type.LP;
            } else if (text.charAt(0) == Type.SUB.symbol) {
                token.type = Type.SUB;
            } else if (text.charAt(0) == Type.RP.symbol) {
                token.type = Type.RP;
            } else if (text.charAt(0) == Type.ADD.symbol) {
                token.type = Type.ADD;
            } else {
                token.type = Type.NUM;
            }
            return token;
        }

    }

    static class AstNode {
        AstNode child1;
        AstNode child2;
        AstNode parent;
        Token token;

        AstNode(Token token) {
            this.token = token;
        }
    }
}