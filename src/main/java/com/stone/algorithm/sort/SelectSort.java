package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/18 14:50
 * description 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：4404
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        selectSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));
    }

    /**
     * 选择排序
     * 第一次从arr[1]~arr[n-1]中选取最小值，与arr[0]交换，
     * 第二次从arr[2]~arr[n-1]中选取最小值，与arr[1]交换，
     * 第i次从arr[i]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
     * 第n-1次从arr[n-1]~arr[n-1]中选取最小值，与arr[n-2]交换，
     * 总共通过n-1次，得到一个按排序码从小到大排列的有序序列。
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
