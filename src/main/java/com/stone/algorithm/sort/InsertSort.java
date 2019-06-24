package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/18 15:47
 * description 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：1511
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        insertSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));

    }

    /**
     * 插入排序
     * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
     * 排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，
     * 将它插入到有序表中的适当位置，使之成为新的有序表。
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }

/*        // 第1轮4,3,2,1--> 3,4,2,1
        int insertVal = arr[1];
        int insertIndex = 1 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.print("第1轮插入：");
        System.out.println(Arrays.toString(arr));

        // 第2轮3,4,2,1--> 2,3,4,1
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.print("第2轮插入：");
        System.out.println(Arrays.toString(arr));

        // 第3轮2,3,4,1--> 1,2,3,4
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.print("第2轮插入：");
        System.out.println(Arrays.toString(arr));*/
    }
}
