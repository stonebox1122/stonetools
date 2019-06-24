package com.stone.algorithm.search;

/**
 * @author stone
 * @date 2019/6/19 15:18
 * description 顺序查找（线性查找）
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,8,2};
        int result = seqSearch(arr, 5);
        System.out.println(result);
    }

    /**
     * 找到第一个满足条件的值就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
