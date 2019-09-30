package com.stone.algorithm.dynamicplanning;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/27 10:04
 * description 0-1背包问题-动态规划
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // 物品重量
        int[] weight = {1, 4, 3};
        // 物品价值
        int[] value = {1500, 3000, 2000};
        // 物品数量
        int n = value.length;
        // 背包容量
        int m = 4;
        // table[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] table = new int[n + 1][m + 1];
        // 记录放入物品的情况
        int[][] path = new int[n + 1][m + 1];

        // 初始化table的第一行和第一列，在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < table.length; i++) {
            table[i][0] = 0;
        }
        for (int i = 0; i < table[0].length; i++) {
            table[0][i] = 0;
        }

        // 根据以下公式来进行动态规划：
        // 当weight[i]>j 时：table[i][j]=table[i-1][j]
        // 当j>=weight[i]时：table[i][j] = max(table[i-1][j-weight[i]]+value[i],table[i-1][j]) ({i,j|0<i<=n,0<=j<=m})
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (weight[i - 1] > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    // 因为i从1开始的，因此公式需要调整为：
                    //table[i][j] = Math.max(table[i-1][j-weight[i-1]]+value[i-1],table[i-1][j]);
                    // 为了记录物品放到背包的情况，就不能直接使用上面的公式（因为不能确定结果来自于max函数的哪部分参数）
                    if (table[i - 1][j] < table[i - 1][j - weight[i - 1]] + value[i - 1]) {
                        table[i][j] = table[i - 1][j - weight[i - 1]] + value[i - 1];
                        path[i][j] = 1;
                    } else {
                        table[i][j] = table[i - 1][j];
                    }
                }
            }
        }

        // 输出规划结果
        for (int[] item : table) {
            System.out.println(Arrays.toString(item));
        }

        // 输出最后放入的哪些物品
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个物品放入背包");
                j -= weight[i - 1];
            }
            i--;
        }

    }
}
