package com.stone.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author stone
 * @date 2019/6/26 13:12
 * description 图
 */
public class Graph {
    // 存储节点
    private List<String> vertexList;
    // 存储图对应的邻接矩阵
    private int[][] edges;
    // 边的数量
    private int numOfEdges;
    // 节点访问标识
    private boolean[] isVisited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 添加节点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个节点的下标
     * @param v2     第二个节点的下标
     * @param weight 边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 返回节点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数目
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点的值
     *
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示邻接矩阵
     */
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 返回第一个邻接节点的下标
     *
     * @param index 节点下标
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     *
     * @param v1 前一个邻接节点的下标
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 一个节点的深度优先遍历
     *
     * @param isVisited
     * @param i
     */
    private void deepFirstSearch(boolean[] isVisited, int i) {
        // 输出当前节点
        System.out.print(getValueByIndex(i) + "->");
        // 将当前节点设置为已访问
        isVisited[i] = true;
        // 找到第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            // 如果下标为w的节点没有被访问，就递归遍历
            if (!isVisited[w]) {
                deepFirstSearch(isVisited, w);
            }

            // 如果下标为w的节点已经被访问，就找一下个
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 所有节点的深度优先遍历
     */
    public void deepFirstSearch() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                deepFirstSearch(isVisited, i);
            }
        }
    }

    /**
     * 一个节点的广度优先遍历
     *
     * @param isVisited
     * @param i
     */
    public void boardFirstSearch(boolean[] isVisited, int i) {
        // 队列头节点下标
        int u;
        // 邻接节点下标
        int w;
        LinkedList queue = new LinkedList();
        // 输出当前节点
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = (int) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 所有节点的广度优先遍历
     */
    public void boardFirstSearch() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                boardFirstSearch(isVisited, i);
            }
        }
    }


    public static void main(String[] args) {
        int n = 8;
//        String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5","6","7","8"};
        Graph graph = new Graph(n);
        // 添加节点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示邻接矩阵
        graph.showGraph();
        graph.deepFirstSearch();
        System.out.println();
        graph.boardFirstSearch();
    }
}
