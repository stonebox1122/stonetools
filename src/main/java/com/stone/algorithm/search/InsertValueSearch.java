package com.stone.algorithm.search;

/**
 * @author stone
 * @date 2019/6/19 16:20
 * description
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int result = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(result);
    }

    /**
     * 插值查找，要求数组有序
     *
     * @param arr   有序数组
     * @param left  左边索引
     * @param right 右边索引
     * @param value 查找的值
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (midValue < value) {
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (midValue > value) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
