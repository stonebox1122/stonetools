package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/18 14:00
 * description 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：14449
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        bubbleSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end-start));
    }

    private static void bubbleSort(int[] arr) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

//            System.out.print("第" + (i + 1) + "轮排序结果：");
//            System.out.println(Arrays.toString(arr));

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
