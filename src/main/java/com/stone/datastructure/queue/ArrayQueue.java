package com.stone.datastructure.queue;

import java.util.Scanner;

/**
 * @author stone
 * @date 2019/6/11 13:58
 * description
 */
public class ArrayQueue {
    /**
     * 队列最大长度
     */
    private int maxSize;
    /**
     * 队列头指针
     */
    private int front;
    /**
     * 队列尾指针
     */
    private int rear;
    /**
     * 存储队列数据的数组
     */
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 入队
     *
     * @param n
     */
    public void add(int n) {
        if (isFull()) {
            throw new RuntimeException("the Queue is full");
        }
        rear++;
        arr[rear] = n;
    }

    /**
     * 出队
     *
     * @return
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("the Queue is empty");
        }
        front++;
        int result = arr[front];
        for (int i = front; i < rear; i++) {
            arr[i] = arr[i + 1];
        }
        front--;
        rear--;
        return result;
    }

    /**
     * 显示队列所有数据
     */
    public void list() {
        if (this.isEmpty()) {
            throw new RuntimeException("the Queue is empty");
        }
        int start = front > -1 ? front : 0;
        for (int i = start; i <= rear; i++) {
            System.out.println("arr[" + i + "]" + arr[i]);
        }
    }

    /**
     * 显示队列头数据
     *
     * @return
     */
    public int head() {
        if (this.isEmpty()) {
            throw new RuntimeException("the Queue is empty");
        }
        return arr[front + 1];
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("p(poll)：从队列取出数据");
            System.out.println("h(head)：查看队列头");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    try {
                        queue.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int result = queue.poll();
                        System.out.println("取出的数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.head();
                        System.out.println("取出的数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}
