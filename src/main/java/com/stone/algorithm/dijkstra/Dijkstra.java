package com.stone.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/28 14:58
 * description 迪杰斯特拉算法
 */
public class Dijkstra {
    public static void main(String[] args) {
        final int N = 65535;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        DGraph dGraph = new DGraph(vertex, matrix);
        dGraph.showGraph();
        dGraph.dijkstra(2);
        dGraph.show();
    }
}

class DGraph {
    // 节点数组
    private char[] vertex;
    // 邻接矩阵
    private int[][] matrix;
    // 已访问顶点集合
    private VisitedVertex visitedVertex;

    public DGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        // 更新index下标顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            // 选择并返回新的访问顶点
            index = visitedVertex.updateArr();
            // 更新index下标顶点到周围顶点的距离和前驱顶点
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和前驱顶点
     *
     * @param index
     */
    public void update(int index) {
        int len = 0;
        // 遍历邻接矩阵matrix[index].length行
        for (int j = 0; j < matrix[index].length; j++) {
            // len = 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离
            len = visitedVertex.getDis(index) + matrix[index][j];
            // 如果j顶点没有被访问过，且len小于出发顶点到j顶点的距离，就需要更新
            if (!visitedVertex.isVisited(j) && len < visitedVertex.getDis(j)) {
                // 更新j顶点的前驱为index顶点
                visitedVertex.updatePre(j, index);
                // 更新出发顶点到j顶点的距离
                visitedVertex.updateDis(j, len);
            }
        }
    }

    public void show(){
        visitedVertex.show();
    }
}

/**
 * 已访问顶点集合
 */
class VisitedVertex {
    // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标,会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * 构造器
     *
     * @param length 顶点的个数
     * @param index  出发顶点对应下标，比如G顶点，下标就是6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 初始化dis数组
        Arrays.fill(dis, 65535);
        // 设置出发顶点的访问距离为0
        this.dis[index] = 0;
        // 设置出发顶点被访问过
        this.already_arr[index] = 1;
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index
     * @return 如果访问过，则返回true
     */
    public boolean isVisited(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如这里的G完后，就是A点作为新的访问顶点
     * @return
     */
    public int updateArr(){
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        // 更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println(Arrays.toString(already_arr));
        System.out.println(Arrays.toString(pre_visited));
        System.out.println(Arrays.toString(dis));
    }
}
