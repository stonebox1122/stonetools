package com.stone.datastructure;

/**
 * @author stone
 * @date 2019/6/4 14:13
 * description
 */
public class LoopNode {
    private int data;
    private LoopNode next = this;

    public LoopNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public LoopNode getNext() {
        return next;
    }

    /**
     * 获取下一个节点
     * @return
     */
    public LoopNode next(){
        return this.next;
    }

    /**
     * 删除下一个节点
     */
    public void removeNext(){
        LoopNode nextNext = this.next.next;
        this.next = nextNext;
    }

    /**
     * 插入一个节点作为当前节点的下一个节点
     * @param node
     */
    public void after(LoopNode node){
        LoopNode nextNext = this.next;
        this.next = node;
        node.next = nextNext;
    }

    public static void main(String[] args) {
        LoopNode node1 = new LoopNode(1);
        LoopNode node2 = new LoopNode(2);
        LoopNode node3 = new LoopNode(3);
        LoopNode node4 = new LoopNode(4);
        LoopNode node5 = new LoopNode(5);

        node1.after(node2);
        node2.after(node3);
        node3.after(node4);

        System.out.println(node1.next.getData());
        System.out.println(node2.next.getData());
        System.out.println(node3.next.getData());
        System.out.println(node4.next.getData());
    }
}
