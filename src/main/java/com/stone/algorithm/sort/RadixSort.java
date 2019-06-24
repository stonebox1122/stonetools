package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/19 13:54
 * description 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{53, 3, 542, 748, 14, 214};
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：41
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        radixSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void radixSort(int[] arr) {
        // 得到数组中最大的数及其位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketEleCounts = new int[10];

        for (int k = 0, n = 1; k < maxLength; k++, n *= 10) {
            // 放入桶
            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] / n % 10;
                bucket[digit][bucketEleCounts[digit]] = arr[i];
                bucketEleCounts[digit]++;
            }
            // 依序取出
            int index = 0;
            for (int i = 0; i < bucketEleCounts.length; i++) {
                if (bucketEleCounts[i] != 0) {
                    for (int j = 0; j < bucketEleCounts[i]; j++) {
                        arr[index] = bucket[i][j];
                        index++;
                    }
                    bucketEleCounts[i] = 0;
                }
            }
            //System.out.println("第" + (k+1) + "轮排序：" + Arrays.toString(arr));
        }


/*        // 第1轮排序，获取每个数字的个位，放入桶中对应位置
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] % 10;
            bucket[digit][bucketEleCounts[digit]] = arr[i];
            bucketEleCounts[digit]++;
        }
        int index = 0;
        for (int i = 0; i < bucketEleCounts.length; i++) {
            if (bucketEleCounts[i] != 0) {
                for (int j = 0; j < bucketEleCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
                bucketEleCounts[i] = 0;
            }
        }
        System.out.println("第1轮排序：" + Arrays.toString(arr));

        // 第2轮排序，获取每个数字的十位，放入桶中对应位置
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] / 10 % 10;
            bucket[digit][bucketEleCounts[digit]] = arr[i];
            bucketEleCounts[digit]++;
        }
        index = 0;
        for (int i = 0; i < bucketEleCounts.length; i++) {
            if (bucketEleCounts[i] != 0) {
                for (int j = 0; j < bucketEleCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
                bucketEleCounts[i] = 0;
            }
        }
        System.out.println("第2轮排序：" + Arrays.toString(arr));

        // 第3轮排序，获取每个数字的百位，放入桶中对应位置
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] / 100 % 10;
            bucket[digit][bucketEleCounts[digit]] = arr[i];
            bucketEleCounts[digit]++;
        }
        index = 0;
        for (int i = 0; i < bucketEleCounts.length; i++) {
            if (bucketEleCounts[i] != 0) {
                for (int j = 0; j < bucketEleCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
                bucketEleCounts[i] = 0;
            }
        }
        System.out.println("第3轮排序：" + Arrays.toString(arr));*/

    }
}
