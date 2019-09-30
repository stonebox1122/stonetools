package com.stone.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author stone
 * @date 2019/7/1 10:28
 * description 骑士周游问题算法
 */
public class HorseChessBoard {
    // 棋盘的列数
    private static int X;
    // 棋盘的行数
    private static int Y;
    // 标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    // 标记棋盘的所有位置是否都被访问
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        // 输出棋盘的最后情况
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游问题算法
     *
     * @param chessBoard 棋盘
     * @param row        马当前位置的行 从0开始
     * @param column     马当前位置的列，从0开始
     * @param step       第几步，初始值为1
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
        // 初始位置
        chessBoard[row][column] = step;
        // 标记该位置已经访问
        visited[row * X + column] = true;
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        // 对ps进行排序，排序的规则是对ps的所有point对象的下一步的位置数目，进行非递减排序
        sort(ps);
        // 遍历ps
        while (!ps.isEmpty()) {
            // 取出下一个可以走的位置
            Point p = ps.remove(0);
            // 判断该位置是否已经访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        // 判断马儿是否完成了任务，使用step和应该走的步数比较，
        // 如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        // 说明：step < X * Y 成立的情况有两种
        // 1.棋盘到目前为止，仍然没有走完
        // 2.棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马儿还能走哪些位置，
     * 并放入到一个集合中(ArrayList), 最多有8个位置
     *
     * @param currPoint
     * @return
     */
    public static ArrayList<Point> next(Point currPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();

        // 判断马是否可以走左上左的位置
        if ((p1.x = currPoint.x - 2) >= 0 && (p1.y = currPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走左上上的位置
        if ((p1.x = currPoint.x - 1) >= 0 && (p1.y = currPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走右上上的位置
        if ((p1.x = currPoint.x + 1) < X && (p1.y = currPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走右上右的位置
        if ((p1.x = currPoint.x + 2) < X && (p1.y = currPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走右下右的位置
        if ((p1.x = currPoint.x + 2) < X && (p1.y = currPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走右下下的位置
        if ((p1.x = currPoint.x + 1) < X && (p1.y = currPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走左下下的位置
        if ((p1.x = currPoint.x - 1) >= 0 && (p1.y = currPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }

        // 判断马是否可以走左下左的位置
        if ((p1.x = currPoint.x - 2) >= 0 && (p1.y = currPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }

    /**
     * 根据当前Point的下一步的所有集合的数目，进行非递减排序，减少回溯次数
     * @param ps
     */
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
