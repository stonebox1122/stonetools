package com.stone.algorithm.prim;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/28 08:57
 * description 普利姆算法
 */
public class Prim {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F','G'};
        int verxs = data.length;
        // 邻接矩阵的关系使用二维数组表示，10000这个大数表示两个点不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MGraph mGraph = new MGraph(verxs);
        MinSpanningTree minSpanningTree = new MinSpanningTree();
        minSpanningTree.createGraph(mGraph, verxs, data, weight);
        minSpanningTree.showGraph(mGraph);
        minSpanningTree.prim(mGraph, 1);
    }
}

class MinSpanningTree {

    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图的节点个数
     * @param data   图的节点值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     *
     * @param graph
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * prim算法，得到最小生成树
     *
     * @param graph 图
     * @param v     从图的第几个节点开始生成'A'->0,'B'-1...
     */
    public void prim(MGraph graph, int v) {
        // 标记节点是否被访问过，默认元素的值都是0，没有被访问过
        int[] visited = new int[graph.verxs];

        // 把当前节点标记为已访问
        visited[v] = 1;

        // h1和h2记录两个节点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;

        // 因为有graph.verxs个节点，故会生成graph.verxs-1条边
        for (int k = 1; k < graph.verxs; k++) {
            // 确定每一次生成的子图（visited[i]==1）和哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    // 如果i节点被访问过，其j节点没有被访问过，且i,j节点之间的权值小于前值
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到一条最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            // 将找到的节点标记为已经访问
            visited[h2] = 1;
            // 重置minWeight
            minWeight = 10000;
        }
    }
}


class MGraph {
    // 图的节点个数
    int verxs;
    // 存放节点数据
    char[] data;
    // 存放边，也就是邻接矩阵
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
