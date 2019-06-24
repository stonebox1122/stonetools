package com.stone.algorithm.recursion;

/**
 * @author stone
 * @date 2019/6/18 10:22
 * description 8皇后问题
 */
public class Queue8 {

    private int max = 8;
    /**
     * 存放皇后放置的位置，是列的位置，行的位置由下标表示
     */
    private int[] arr = new int[max];

    /**
     * 输出皇后放置的位置，是列的位置
     */
    public void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 判断放置的第n个皇后，是否和之前放置的所有皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            // arr[i] == arr[n] 判断第n个皇后是否与第i个皇后在同一列
            // Math.abs(n-i) == Math.abs(arr[n] - arr[i]) 判断第n个皇后是否与第i个皇后在同一斜线
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 放置第n个皇后
     * 特别注意：每一次递归时，都有for循环，因此会有回溯
     * @param n
     */
    public void check(int n){
        // 如果已经放好了，则打印输出
        if (n == max) {
            print();
            return;
        }

        // 依次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            // 从第1列开始
            arr[n] = i;
            // 如果不冲突，则放置下一个
            if (judge(n)){
                check(n+1);
            }
            // 如果冲突，继续循环，去检查下1列是否冲突
        }
    }

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }
}
