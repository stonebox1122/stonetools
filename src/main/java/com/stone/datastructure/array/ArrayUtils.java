package com.stone.datastructure.array;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/3 18:48
 * description
 */
public class ArrayUtils {

    /**
     * 数组长度
     * @param arr 数组名称
     * @return
     */
    public static int getLength(Object[] arr){
        return arr.length;
    }

    /**
     * 打印数组内容到控制台
     * @param arr
     */
    public static void getAll(Object[] arr){
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 获取指定下标的元素
     * @param arr
     * @param index
     * @return
     */
    public static Object getWithIndex(Object[] arr, int index){
        if (index > arr.length - 1 || index < 0) {
            throw new RuntimeException("下标越界");
        }
        return arr[index];
    }

    /**
     * 在数组末尾添加一个元素
     * @param arr
     * @param element
     * @return
     */
    public static Object[] addToLast(Object[] arr, Object element){
        Object[] newArr = new Object[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = element;
        return newArr;
    }

    /**
     * 在数组指定位置插入元素
     * @param arr
     * @param element
     * @param index
     * @return
     */
    public static Object[] addWithIndex(Object[] arr, Object element, int index){
        if (index > arr.length || index < 0) {
            throw new RuntimeException("下标越界");
        }
        Object[] newArr = new Object[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            if (i < index){
                newArr[i] = arr[i];
            } else {
                newArr[i + 1] = arr[i];
            }
        }
        newArr[index] = element;
        return newArr;
    }

    /**
     * 删除数组最后一个元素
     * @param arr
     * @return
     */
    public static Object[] deleteLast(Object[] arr){
        if (arr.length == 0){
            throw new RuntimeException("下标越界");
        }
        Object[] newArr = new Object[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /**
     * 删除数组指定位置元素
     * @param arr
     * @param index
     * @return
     */
    public static Object[] deleteWithIndex(Object[] arr, int index){
        if (index > arr.length - 1 || index < 0) {
            throw new RuntimeException("下标越界");
        }
        Object[] newArr = new Object[arr.length - 1];
        for (int i = 0; i < newArr.length; i++) {
            if (i < index){
                newArr[i] = arr[i];
            } else {
                newArr[i] = arr[i + 1];
            }
        }
        return newArr;
    }

    /**
     * 线性查找指定元素
     * @param arr
     * @param element
     * @return
     */
    public static int linearSearch(Object[] arr, Object element){
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (element.equals(arr[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 整数类型数据排序
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 二分查找法
     * @param arr
     * @param element
     * @return
     */
    public static int binarySearch(int[] arr, int element){
        int[] sortArr = sort(arr);
        int index = -1;
        int head = 0;
        int tail = arr.length - 1;
        while (true) {
            int mid = (head + tail) / 2;
            if (head >= tail){
                break;
            }
            if (sortArr[mid] == element){
                index = mid;
                break;
            }
            if(sortArr[mid] > element){
                tail = mid - 1;
            }
            if (sortArr[mid] < element){
                head = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Object[] arr = new Object[]{"aa","bb","cc","dd"};
        ArrayUtils.getAll(ArrayUtils.addToLast(arr, "ee"));
        ArrayUtils.getAll(ArrayUtils.addWithIndex(arr,"ee",2));
        ArrayUtils.getAll(ArrayUtils.deleteLast(arr));
        ArrayUtils.getAll(ArrayUtils.deleteWithIndex(arr,2));
        System.out.println(ArrayUtils.getWithIndex(arr, 2));
        System.out.println(ArrayUtils.linearSearch(arr, "dd"));
        int[] arr2 = new int[]{9,8,7,6,5,4,3};
        System.out.println(Arrays.toString(ArrayUtils.sort(arr2)));
        System.out.println(ArrayUtils.binarySearch(arr2, 10));
    }
}
