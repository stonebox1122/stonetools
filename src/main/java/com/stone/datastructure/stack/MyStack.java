package com.stone.datastructure.stack;

/**
 * @author stone
 * @date 2019/6/4 13:16
 * description
 */
public class MyStack {

    private int[] arr;

    public MyStack() {
        this.arr = new int[0];
    }

    public MyStack(int[] arr) {
        this.arr = arr;
    }

    /**
     * 入栈
     * @param element
     */
    public void push(int element){
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = element;
        arr = newArr;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if (arr.length == 0){
            throw new RuntimeException("下标越界");
        }
        int element = arr[arr.length - 1];
        int[] newArr = new int[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            newArr[i] = arr[i];
        }
        arr =newArr;
        return element;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public int peek(){
        if (arr.length == 0){
            throw new RuntimeException("下标越界");
        }
        return arr[arr.length - 1];
    }

    /**
     * 栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return arr.length == 0;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        System.out.println(myStack.isEmpty());

        myStack.push(9);
        myStack.push(8);
        myStack.push(7);

        System.out.println(myStack.isEmpty());

        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());

        System.out.println(myStack.isEmpty());
    }
}
