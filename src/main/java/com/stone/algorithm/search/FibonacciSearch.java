package com.stone.algorithm.search;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/19 16:47
 * description 斐波那契查找
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,3,5,8};
        System.out.println(fibonacciSearch(arr,8));
    }

    /**
     * 斐波那契查找
     * @param arr 有序数组
     * @param value 查找的值
     * @return
     */
    public static int fibonacciSearch(int[] arr,int value){
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        // 获取到适用于当前数组的斐波那契分割数组的长度
        int k = 0;
        int f[] = getFibonacci(20);
        while (f[k] - 1 < high){
            k++;
        }

        // 因为斐波那契数列不是连续的，所以上面获取的长度有可能大于传入数组的长度
        // 需要构建一个新的数组，不足部分使用原数组最后一个值填充
        // 后续查找使用新的数组，以便使用斐波那契分割
        int[] temp = Arrays.copyOf(arr,f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 使用斐波那契分割数组，前部分为f[k-1]-1，后部分为f[k-2]-1
        while (low <= high) {
            mid=low+f[k-1]-1;
            if (value<temp[mid]){
                high=mid-1;
                k--;
            }else if (value>temp[mid]){
                low=mid+1;
                k-=2;
            } else {
                if (mid<=high){
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    /**
     * 获取一个斐波那契数列
     * @param size 斐波那契数列长度
     * @return 返回一个数组
     */
    public static int[] getFibonacci(int size){
        int[] fibonacci = new int[size];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < size; i++) {
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }
}
