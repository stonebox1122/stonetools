package com.stone.datastructure.linkedlist;

/**
 * @author stone
 * @date 2019/6/4 14:13
 * description
 */
public class Node {
    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    /**
     * 添加节点到最后
     * @return
     */
    public Node append(Node node){
        this.next = node;
        return this.next;

//        Node currrentNode = this;
//        while (true){
//            Node nextNode = currrentNode.next;
//            if (nextNode == null){
//                break;
//            }
//            currrentNode = nextNode;
//        }
//        currrentNode.next = node;
//        return this;
    }

    /**
     * 获取下一个节点
     * @return
     */
    public Node next(){
        return this.next;
    }

    /**
     * 判断是否是最后一个节点
     * @return
     */
    public boolean isLast(){
        return next == null;
    }

    /**
     * 删除下一个节点
     */
    public void removeNext(){
        Node nextNext = this.next.next;
        this.next = nextNext;
    }

    /**
     * 显示所有节点信息
     */
    public void show(){
        Node currrentNode = this;
        while (true){
            System.out.println(currrentNode.getData());
            Node nextNode = currrentNode.next;
            if (nextNode == null){
                break;
            }
            currrentNode = nextNode;
        }
    }

    /**
     * 插入一个节点作为当前节点的下一个节点
     * @param node
     */
    public void after(Node node){
        Node nextNext = this.next;
        this.next = node;
        node.next = nextNext;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        System.out.println(node1.append(node2).append(node3).append(node4).getData());
        System.out.println(node1.getNext().getNext().getData());

        System.out.println(node1.isLast());
        System.out.println(node1.next.next.isLast());

        node1.show();

//        node1.removeNext();
//        node1.show();

        node2.after(node5);
        node1.show();
    }
}
