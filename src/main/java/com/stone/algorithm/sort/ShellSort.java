package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/18 16:37
 * description 希尔排序（优化插入排序）
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：8777
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        shellSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));

        int[] arr2 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println(Arrays.toString(arr2));
        shellSort2(arr2);
        System.out.println(Arrays.toString(arr2));

        // 耗费时间：4
        start = System.currentTimeMillis();
        shellSort2(arr1);
        end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));

    }

    /**
     * 交换法希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }


/*        // 第1轮8, 9, 1, 7, 2, 3, 5, 4, 6, 0 --> 3, 5, 1, 6, 0, 8, 9, 4, 7, 2
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第1轮排序：" + Arrays.toString(arr));

        // 第2轮3, 5, 1, 6, 0, 8, 9, 4, 7, 2 --> 0, 2, 1, 4, 3, 5, 7, 6, 9, 8
        temp = 0;
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮排序：" + Arrays.toString(arr));

        // 第3轮0, 2, 1, 4, 3, 5, 7, 6, 9, 8 --> 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮排序：" + Arrays.toString(arr));*/
    }

    /**
     * 移动法希尔排序（分组后使用插入排序）
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i - gap;
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                arr[insertIndex + gap] = insertVal;
            }
        }
    }
}
