package com.stone;

import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * @author stone
 * @date 2019/6/6 15:28
 * description
 */
public class Test {

    /**
     * 堆排序
     * <p>
     * 1\. 先将初始序列K[1..n]建成一个大顶堆, 那么此时第一个元素K1最大, 此堆为初始的无序区.
     * 2\. 再将关键字最大的记录K1 (即堆顶, 第一个元素)和无序区的最后一个记录 Kn 交换, 由此得到新的无序区K[1..n−1]和有序区K[n], 且满足K[1..n−1].keys⩽K[n].key
     * 3\. 交换K1 和 Kn 后, 堆顶可能违反堆性质, 因此需将K[1..n−1]调整为堆. 然后重复步骤②, 直到无序区只有一个元素时停止.
     *
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            max_heapify(arr, i);

            int temp = arr[0];
            arr[0] = arr[i - 1];
            arr[i - 1] = temp;
        }
    }

    private static void max_heapify(int[] arr, int limit) {
        if (arr.length <= 0 || arr.length < limit) {
            return;
        }
        int parentIdx = limit / 2;

        for (; parentIdx >= 0; parentIdx--) {
            if (parentIdx * 2 >= limit) {
                continue;
            }
            //左子节点位置
            int left = parentIdx * 2;
            //右子节点位置，如果没有右节点，默认为左节点位置
            int right = (left + 1) >= limit ? left : (left + 1);

            int maxChildId = arr[left] >= arr[right] ? left : right;
            //交换父节点与左右子节点中的最大值
            if (arr[maxChildId] > arr[parentIdx]) {
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
        System.out.println("Max_Heapify: " + Arrays.toString(arr));
    }


    public static void MaxHeapify(int[] a,int index,int size){
        int l=2*index;
        int r=2*index+1;
        int largest=index;
        if(l<=size && a[l]>a[index]){
            largest=l;
        }
        if(r<=size && a[r]>a[largest]){
            largest=r;
        }
        if(largest!=index){
            int temp=a[largest];
            a[largest]=a[index];
            a[index]=temp;
            MaxHeapify(a,largest,size);
        }
    }
    public static void HeapBuild(int[] a,int size){
        for(int i=size/2;i>=1;i--){
            MaxHeapify(a,i,size);
        }
    }
    public static void Sort(int[] a,int size) {
        HeapBuild(a, size);
        for (int i = size; i >= 2; i--) {
            int temp = a[i];
            a[i] = a[1];
            a[1] = temp;
            MaxHeapify(a, 1, i - 1);
        }
    }

    public int removeDuplicates(int[] nums) {
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        int i=0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            nums[i]=entry.getKey();
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{9,6,8,7,0,1,10,4,2};
        System.out.println(Arrays.toString(arr1));
        Test.Sort(arr1,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
        String str = "abcd";
        System.out.println(str.substring(0,4));

        byte[] byteValue = {-81, -47, -49, -47, -49, -47, -55, -51, -57, 36, -24, -80, -3, 28, -60, -26, 28};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < byteValue.length; i++) {
            stringBuilder.append(StringUtils.leftPad(Integer.toBinaryString(byteValue[i] & 0xff), 8, '0'));
        }
        System.out.println(stringBuilder.toString());
        System.out.println(new BigInteger(1, byteValue).toString(2));

        int[] arr2 = new int[]{1,2,3,4,5,6,7};
        rotate(arr2,3);

        int[] arr3 = new int[]{99,99};
        containsNearbyDuplicate(arr3,2);

        List<Integer> list = new LinkedList<>();
        list.size();
    }

    public static void rotate(int[] nums, int k) {
        for(int i=1;i<=k;i++){
            int temp =nums[nums.length-1];
            for(int j= nums.length-1;j>0;j--){
                nums[j]=nums[j-1];
            }
            nums[0]=temp;
        }
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int len=nums.length-1;
        for(int i=0;i<=len;i++){
            if(i+k<=len){
                for(int j=i+1;j<=i+k;j++){
                    if(nums[i]==nums[j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
