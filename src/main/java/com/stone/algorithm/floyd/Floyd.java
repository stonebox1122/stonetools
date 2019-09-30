package com.stone.algorithm.floyd;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/28 17:10
 * description 弗洛伊德算法，计算各点到其他点的最短路径
 */
public class Floyd {
    public static void main(String[] args) {
        final int N = 65535;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        FGraph fGraph = new FGraph(vertex.length, vertex, matrix);
        fGraph.show();
        fGraph.floyd();
        fGraph.show();
    }
}

class FGraph {
    // 存放顶点
    private char[] vertex;
    // 存放从各个顶点到其他顶点的距离
    private int[][] dis;
    // 存放到达目标顶点的前驱顶点下标
    private int[][] pre;

    public FGraph(int length, char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + ",");
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.print("[" + vertex[i] + "->" + vertex[j] + "最短路径：" + dis[i][j] + "],");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd(){
        int len = 0;
        // 对中间顶点遍历，k为中间顶点下标
        for (int k = 0; k < dis.length; k++) {
            // 各个起点下标
            for (int i = 0; i < dis.length; i++) {
                // 各个终点下标
                for (int j = 0; j < dis.length; j++) {
                    // 从顶点i出发，经过中间顶点k，到达顶点j的距离
                    len = dis[i][k] + dis[k][j];
                    // 如果从i经过k到j的距离小于从i到j的直连距离，则更新距离和前驱顶点
                    if (len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }

            }
        }
    }
}
