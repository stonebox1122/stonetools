package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/19 08:59
 * description
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{5,6,4,7,5,2,9,1};
        int[] arr = new int[]{10,9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：69
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        quickSort(arr1,0,arr1.length-1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end-start));
    }

    /**
     * 快速排序
     * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     *
     * @param arr   待排序数组
     * @param left  左边起始位置
     * @param right 右边起始位置
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = arr[left];
            int l = left;
            int r = right;
            int temp;
            while (l < r) {
                // 注意：需要先移动右边的指针，这样左右指针才会在最后停在小的数据上面
                while (l < r && pivot <= arr[r]) {
                    r--;
                }
                while (l < r && arr[l] <= pivot) {
                    l++;
                }
                if (l != r) {
                    temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;
                }
            }
            if (l == r) {
                arr[left] = arr[l];
                arr[l] = pivot;
            }
            quickSort(arr, left, l - 1);
            quickSort(arr, l + 1, right);
        }
    }
}
