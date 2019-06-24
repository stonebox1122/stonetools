package com.stone.datastructure.stack;

import java.util.Scanner;

/**
 * @author stone
 * @date 2019/6/13 14:25
 * description 加减乘除运算，不支持负数的运算
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "5*5*5+2-3*4-5-6/2+5";
        ArrayStack2 numStack = new ArrayStack2(100);
        ArrayStack2 operStack = new ArrayStack2(100);
        int length = expression.length();
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char c = ' ';
        String keepNum = "";

        for (int i = 0; i < length; i++) {
            c = expression.substring(i).charAt(0);
            if (numStack.isOper(c)) {
                if (operStack.isEmpty()) {
                    operStack.push(c);
                } else {
                    if (operStack.priority(c) > operStack.priority(operStack.peek())) {
                        operStack.push(c);
                    } else {
                        // 此处要计算两次，假如表达式是5-2*5+1
                        // 如果运算一次，则结果为5-（10+1）=-6 错误
                        // 如果运算两次，则结果为5-10=-5 -5+1=-4 正确
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        numStack.push(result);

                        if (numStack.size() >= 1) {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            result = numStack.cal(num1, num2, oper);
                            numStack.push(result);
                        }

                        operStack.push(c);
                    }
                }
            } else {
                //numStack.push(c - 48);
                //处理多位数
                keepNum += c;
                if (i == length - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (numStack.isOper(expression.substring(i + 1).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
        }

        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }

        System.out.println(numStack.peek());
    }
}


class ArrayStack2 {

    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 获取栈顶值
     *
     * @return
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 栈的长度
     *
     * @return
     */
    public int size() {
        return top;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法出栈");
        }

        int result = stack[top];
        top--;
        return result;
    }

    /**
     * 遍历栈
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    /**
     * 运算符优先级
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否是操作符
     *
     * @param oper
     * @return
     */
    public boolean isOper(int oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}