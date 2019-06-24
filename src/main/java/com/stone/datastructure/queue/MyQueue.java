package com.stone.datastructure.queue;

/**
 * @author stone
 * @date 2019/6/4 13:58
 * description
 */
public class MyQueue {
    private int[] arr;

    public MyQueue() {
        this.arr = new int[0];
    }

    public MyQueue(int[] arr) {
        this.arr = arr;
    }

    /**
     * 入队
     * @param element
     */
    public void add(int element){
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = element;
        arr = newArr;
    }

    /**
     * 出队
     * @return
     */
    public int poll(){
        if (arr.length == 0){
            throw new RuntimeException("下标越界");
        }
        int element = arr[0];
        int[] newArr = new int[arr.length - 1];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i + 1];
        }
        arr =newArr;
        return element;
    }

    public boolean isEmpty(){
        return arr.length == 0;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.add(9);
        myQueue.add(8);
        myQueue.add(7);

        System.out.println(myQueue.poll());
        myQueue.add(6);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.isEmpty());
    }
}
