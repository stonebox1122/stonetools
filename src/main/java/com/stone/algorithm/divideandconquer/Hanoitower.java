package com.stone.algorithm.divideandconquer;

/**
 * @author stone
 * @date 2019/6/26 16:29
 * description 汉诺塔
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第" + num + "个盘从" + a + "->" + c);
        } else {
            // 如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
            // 先把上面的盘 A->B
            hanoiTower(num - 1, a, c, b);
            // 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            // 把B塔的所有盘 B->C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
