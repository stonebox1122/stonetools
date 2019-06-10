package com.stone.datastructure;

/**
 * @author stone
 * @date 2019/6/4 16:26
 * description
 */
public class Recursion {

    public static int fibonacci(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 汉诺塔
     *
     * @param n    盘子数量
     * @param from 开始柱子
     * @param in   中间柱子
     * @param to   目标柱子
     */
    public static void hanoi(int n, char from, char in, char to) {
        if (n <= 0) {
            return;
        }
        if (n == 1) {
            System.out.println("第1个盘子从" + from + "移动到" + to);
        } else {
            hanoi(n - 1, from, to, in);
            System.out.println("第" + n + "个盘子从" + from + "移动到" + to);
            hanoi(n - 1, in, from, to);
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(7));
        hanoi(3, 'a', 'b', 'c');
    }
}
