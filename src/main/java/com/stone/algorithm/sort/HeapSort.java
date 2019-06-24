package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/21 15:25
 * description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 8, 5, 9};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：20
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        heapSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end-start));
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int temp = 0;

        // 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        // 这里需要从最后一个非叶子节点开始，循环进行
        // 第1轮：4, 6, 8, 5, 9 --> 4, 9, 8, 5, 9 --> 4, 9, 8, 5, 6
        // 第2轮：4, 9, 8, 5, 6 --> 9, 9, 8, 5, 6 --> 9, 6, 8, 5, 6 --> 9, 6, 8, 5, 4
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        // 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        // 第1轮：9, 6, 8, 5, 4 --> 4, 6, 8, 5, 9 --> 8, 6, 4, 5, 9
        // 第2轮：              --> 5, 6, 4, 8, 9 --> 6, 5, 4, 8, 9
        // 第3轮：              --> 4, 5, 6, 8, 9 --> 5, 4, 6, 8, 9
        // 第4轮：              --> 4, 5, 6, 8, 9 --> 4, 5, 6, 8, 9
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //System.out.println("->" + Arrays.toString(arr));
            // 排除尾部交换后数字，只有第一个数字不满足大顶堆要求，故从第一个数字开始，长度就不包含末尾交换后数字
            adjustHeap(arr, 0, i);
            //System.out.println("-->" + Arrays.toString(arr));
        }
    }

    /**
     * 调整数组为大顶堆
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点在数组中索引，2 * i + 1为左子节点，2 * i + 2为右子节点
     * @param length 继续调整元素的个数
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (temp < arr[j]) {
                arr[i] = arr[j];
                i = j;
            } else {
                // 由于是从左至右，从下至上调整，故此处可以break
                break;
            }
            //System.out.println("*>" + Arrays.toString(arr));
        }
        arr[i] = temp;
        //System.out.println("**>" + Arrays.toString(arr));
    }
}
