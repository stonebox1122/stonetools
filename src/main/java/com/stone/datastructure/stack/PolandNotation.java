package com.stone.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author stone
 * @date 2019/6/17 13:30
 * description 计算逆波兰表达式
 */
public class PolandNotation {

    /**
     * 将逆波兰表达式转换为List
     *
     * @param exp 逆波兰表达式
     * @return
     */
    public static List<String> getList(String exp) {
        List<String> list = new ArrayList<>();
        String[] splits = exp.split(" ");
        for (String split : splits) {
            list.add(split);
        }
        return list;
    }

    /**
     * 计算表达式
     *
     * @param list 由逆波兰表达式转换而来
     * @return
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num1 - num2;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("符号错误！");
                }
                stack.push(result + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将中缀表达式转换为List
     *
     * @param exp 中缀表达式
     * @return
     */
    public static List<String> toInfixExp(String exp) {
        List<String> list = new ArrayList<>();
        int i = 0;
        char ch = ' ';
        String str = "";
        do {
            if (exp.charAt(i) < 48 || exp.charAt(i) > 57) {
                list.add(exp.charAt(i) + "");
                i++;
            } else {
                str = "";
                while (i < exp.length() && exp.charAt(i) >= 48 && exp.charAt(i) <= 57) {
                    str += exp.charAt(i);
                    i++;
                }
                list.add(str);
            }
        } while (i < exp.length());
        return list;
    }

    /**
     * 获取操作符的优先级
     *
     * @param exp 操作符
     * @return
     */
    public static int getPriority(String exp) {
        int result = 0;
        switch (exp) {
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "*":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 将中缀表达式的list转换为后缀表达式的list
     *
     * @param exp 中缀表达式的list
     * @return
     */
    public static List<String> toSuffixExp(List<String> exp) {
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (String item : exp) {
            // 如果是数字则直接入列表
            if (item.matches("\\d+")) {
                list.add(item);
                // 如果是左括号，则直接入栈
            } else if (item.equals("(")) {
                stack.push(item);
                // 如果是右括号，则依次将栈中不是左括号的元素出栈加入列表，直到遇到左括号，将左括号出栈
            } else if (item.equals(")")) {
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop();
                // 如果是运算符，则需要栈中运算符进行比较，比栈顶运算符小或等，则将栈中运算符出栈加入列表，否则入栈
            } else {
                while (stack.size() != 0 && getPriority(item) <= getPriority(stack.peek())){
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }

        // 将栈中剩余元素出栈加入列表
        while (stack.size() != 0) {
            list.add(stack.pop());
        }
        return list;
    }

    public static void main(String[] args) {
        // (3+4)×5-6 对应的逆波兰表达式为3 4 + 5 * 6 -
        String suffixExp = "30 4 + 5 * 6 -";
        List<String> list = getList(suffixExp);
        int result = calculate(list);
        System.out.println(result);
        String exp = "1+((2+3)*4)-5";
        System.out.println(toSuffixExp(toInfixExp(exp)));
        System.out.println(calculate(toSuffixExp(toInfixExp(exp))));

    }
}

