package com.stone.algorithm.sort;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/19 10:56
 * description
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        System.out.println(Arrays.toString(arr));
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));

        // 耗费时间：27
        int[] arr1 = new int[80000];
        int[] temp1 = new int[arr1.length];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        mergeSort(arr1,0,arr1.length-1,temp1);
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end-start));
    }

    /**
     * 归并排序，采用递归的方式先分解，再进行合并
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right) {
            int mid=(left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并，假定数组从某个位置分隔，左边和右边都是有序的，将左边和右边进行有序合并。
     * @param arr 待排序数组
     * @param left 左边起始位置，左边起始到中间是有序的
     * @param mid 中间位置
     * @param right 右边末尾位置，中间到右边末尾是有序的
     * @param temp 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i=left;
        int j=mid+1;
        int index=0;
        while (i <= mid && j <= right) {
            if (arr[i]<=arr[j]){
                temp[index]=arr[i];
                index++;
                i++;
            }else {
                temp[index]=arr[j];
                index++;
                j++;
            }
        }

        while (i <= mid) {
            temp[index]=arr[i];
            index++;
            i++;
        }

        while (j <= right) {
            temp[index]=arr[j];
            index++;
            j++;
        }

        //System.out.println(left+";"+right+";"+index); //index=right-left+1
        for (int k = 0; k < index; k++) {
            arr[left+k]=temp[k];
        }
    }
}
