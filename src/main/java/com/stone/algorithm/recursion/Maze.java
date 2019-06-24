package com.stone.algorithm.recursion;

/**
 * @author stone
 * @date 2019/6/17 16:16
 * description 迷宫回溯
 */
public class Maze {

    /**
     * 起点位置：map[1][1]，终点位置：map[6][5]
     * 约定：map[i][j]为0表示该点没有走过，为1表示墙，为2表示可以走，为3表示已经走过但走不通
     * 策略：下->右->上->左，如果该点走不通，再回溯
     *
     * @param map 表示地图
     * @param i   起始行位置
     * @param j   起始列位置
     * @return 找到路径返回true
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 假定该点可以走通，然后按照策略 下->右->上->左 进行
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else {
                    // 走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        // 模拟迷宫
        int[][] map = new int[8][7];
        int colNum = map.length;
        int rowNum = map[0].length;

        // 1表示墙,上下墙
        for (int i = 0; i < rowNum; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 1表示墙,左右墙
        for (int i = 0; i < colNum; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 输出地图
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        findWay(map, 1, 1);
        System.out.println("===========");

        // 输出找到的路径
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
