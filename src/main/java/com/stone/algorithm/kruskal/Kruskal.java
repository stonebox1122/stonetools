package com.stone.algorithm.kruskal;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/28 10:45
 * description 克鲁斯卡尔算法
 */
public class Kruskal {
    // 边的个数
    private int edegNum;
    // 节点数组
    private char[] vertexs;
    // 邻接矩阵
    private int[][] matrix;
    // 使用INF表示两个节点不能联通
    private static final int INF = Integer.MAX_VALUE;

    public Kruskal(char[] vertexs, int[][] matrix) {
        // 初始化节点和边的个数
        int vLen = vertexs.length;

        // 初始化节点，使用拷贝的方式
        this.vertexs = new char[vLen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        // 初始化边，使用拷贝的方式
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边的个数
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edegNum++;
                }
            }
        }
    }

    /**
     * 显示邻接矩阵
     */
    public void showGraph() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序，使用冒泡法
     *
     * @param edges 边集合
     */
    public void sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j+1].weight) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取节点对应的下标
     *
     * @param ch 节点的值，比如'A'
     * @return 节点对应的下标，如果找不到返回-1
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到数组中，然后遍历
     * 通过matrix邻接矩阵来获取
     * Edge[] 形式 [['A','B',12],['B','F',7]...]
     *
     * @return
     */
    public Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[edegNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edge(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的节点的终点的下标，用于判断两个节点的终点是否相同
     * @param ends 记录各个节点对应的终点是哪个，是在遍历过程中逐步形成的
     * @param i 传入的节点对应的下标
     * @return
     */
    public int getEnd(int[] ends,int i){
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal(){
        // 最后结果（最小生成树）数组索引
        int index = 0;
        // 最后结果（最小生成树）数组
        Edge[] resultEdges = new Edge[edegNum];
        // 当前最小生成树中每个节点在最小生成树中的终点
        int[] ends = new int[edegNum];

        // 获取图中所有边的集合
        Edge[] edges = getEdges();
        // 按照边的权值大小升序排序
        sortEdges(edges);

        // 遍历edges数组，将边加入到最小生成树中时，判断准备加入的边是否形成了回路
        // 如果没有形成回路，就加入结果数组
        for (int i = 0; i < edegNum; i++) {
            // 获取到第i条边的第1个节点下标
            int p1 = getPosition(edges[i].vertex1);
            // 获取到第i条边的第2个节点下标
            int p2 = getPosition(edges[i].vertex2);

            // 获取p1节点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取p2节点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            // 如果m不等于n，表示不构成回路
            if (m != n) {
                // 连接后，重设m节点在已有最小生成树中的终点
                ends[m] = n;
                // 将这条边加入到结果数组
                resultEdges[index++] = edges[i];
            }
        }
        // 输入结果数组
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(resultEdges[i]);
        }
    }

    public static void main(String[] args) {
        final int INF = Integer.MAX_VALUE;
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.showGraph();
        kruskal.kruskal();
    }
}

/**
 * 表示一条边
 */
class Edge {
    char vertex1;
    char vertex2;
    int weight;

    public Edge(char vertex1, char vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", weight=" + weight +
                '}';
    }
}

