package com.stone.datastructure;

/**
 * @author stone
 * @date 2019/6/4 15:35
 * description
 */
public class DoubleNode {

    private int data;
    private DoubleNode pre = this;
    private DoubleNode next = this;

    public DoubleNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public DoubleNode getPre() {
        return pre;
    }

    public DoubleNode getNext() {
        return next;
    }

    /**
     * 增加节点
     * @param node
     */
    public void after(DoubleNode node){
        DoubleNode nextNext = this.next;

        this.next = node;
        node.pre = this;

        node.next = nextNext;
        nextNext.pre = node;
    }

    public static void main(String[] args) {
        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);

        System.out.println(n1.getPre().getData());
        System.out.println(n1.getData());
        System.out.println(n1.getNext().getData());

        n1.after(n2);
        System.out.println(n1.getPre().getData());
        System.out.println(n1.getData());
        System.out.println(n1.getNext().getData());

        n1.after(n3);
        System.out.println(n1.getPre().getData());
        System.out.println(n1.getData());
        System.out.println(n1.getNext().getData());
    }
}
