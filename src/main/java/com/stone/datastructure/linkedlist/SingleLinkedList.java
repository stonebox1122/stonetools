package com.stone.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author stone
 * @date 2019/6/11 16:54
 * description
 */
public class SingleLinkedList {
    // 初始化一个头结点，不存放具体的数据
    private HeroNode head = new HeroNode(0, "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 添加节点（不考虑编号顺序）
     * 1.找到当前链表的最后节点
     * 2.将最后节点的next指向新节点
     *
     * @param node
     */
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    /**
     * 添加节点（考虑编号顺序）
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getId() > node.getId()) {
                break;
            } else if (temp.getNext().getId() == node.getId()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            System.out.println(node.getId() + "已经存在，无法添加");
        } else {
            node.setNext(temp.getNext());
            temp.setNext(node);
        }
    }

    /**
     * 修改节点，需要编号相同
     *
     * @param node
     */
    public void update(HeroNode node) {
        if (head.getNext() == null) {
            System.out.println("the List is empty.");
            return;
        }

        HeroNode temp = head.getNext();
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getId() == node.getId()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }

        if (flag) {
            temp.setName(node.getName());
        } else {
            System.out.println("不能更新，不存在编号：" + node.getId());
        }
    }

    /**
     * 删除节点
     *
     * @param id
     */
    public void delete(int id) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getId() == id) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.println("不能删除，不存在编号：" + id);
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (head.getNext() == null) {
            System.out.println("the List is empty");
            return;
        }

        HeroNode temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    /**
     * 获取单链表节点的个数（如果是带头结点的单链表，则不统计头结点）
     *
     * @param head 链表的头结点
     * @return 返回有效节点的个数
     */
    public int getLength(HeroNode head) {
        if (head.getNext() == null) {
            return 0;
        }

        HeroNode temp = head.getNext();
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.getNext();
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个结点
     * 1.获取链表总长度length
     * 2.从头开始找第length-index个节点
     *
     * @param head  链表的头结点
     * @param index 倒数第K个节点
     * @return 返回找到的节点，没有找到返回null
     */
    public HeroNode findCountDownNode(HeroNode head, int index) {
        if (head.getNext() == null || index <= 0) {
            return null;
        }

        int length = getLength(head) - index;
        if (length < 0) {
            return null;
        }

        HeroNode temp = head.getNext();
        for (int i = 0; i < length; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * 单链表反转
     *
     * @param head
     * @return
     */
    public SingleLinkedList reverse(HeroNode head) {
        if (head.getNext() == null) {
            return null;
        }

        SingleLinkedList list = new SingleLinkedList();
        int length = getLength(head);
        for (int i = 1; i <= length; i++) {
            // 找到原链表最后一个节点
            HeroNode countDownNode = findCountDownNode(head, 1);
            // 将最后一个节点加到新链表
            list.add(countDownNode);
            // 删除原链表最后一个节点
            delete(countDownNode.getId());
        }
        return list;
    }

    /**
     * 单链表反转
     * 思路:
     * 1. 先定义一个节点 reverseHead = new HeroNode();
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端.
     * 3. 原来的链表的head.next = reverseHead.next
     *
     * @param head
     */
    public void reverseList(HeroNode head) {
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }

        HeroNode reverseHead = new HeroNode(0, "");
        HeroNode curr = head.getNext();
        HeroNode next = null;
        while (curr != null) {
            next = curr.getNext();
            curr.setNext(reverseHead.getNext());
            reverseHead.setNext(curr);
            curr = next;
        }
        head.setNext(reverseHead.getNext());
    }

    /**
     * 逆序打印链表，不改变链表的结构
     * 思路
     * 1. 上面的题的要求就是逆序打印单链表.
     * 2. 方式1： 先将单链表进行反转操作，然后再遍历即可，这样的做的问题是会破坏原来的单链表的结构，不建议
     * 3. 方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果.
     * 举例演示栈的使用 Stack
     *
     * @param head
     */
    public void reversePrint(HeroNode head) {
        if (head.getNext() == null) {
            return;
        }

        // 初始化一个栈，遍历链表入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.getNext();
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并有序单链表，合并后也有序
     *
     * @param list1
     * @param list2
     * @return
     */
    public static SingleLinkedList merge(SingleLinkedList list1, SingleLinkedList list2) {
        HeroNode head1 = list1.getHead();
        HeroNode head2 = list2.getHead();
        HeroNode curr1 = head1.getNext();
        HeroNode curr2 = head2.getNext();
        SingleLinkedList mergeList = new SingleLinkedList();
        HeroNode mergeCurr = mergeList.getHead();

        if (curr1 == null && curr2 == null) {
            return null;
        }
        if (curr1 == null) {
            return list2;
        }
        if (curr2 == null) {
            return list1;
        }

        HeroNode next1 = null;
        HeroNode next2 = null;
        while (curr1 != null && curr2 != null) {
            next1 = curr1.getNext();
            next2 = curr2.getNext();
            if (curr1.getId() <= curr2.getId()) {
                mergeCurr.setNext(curr1);
                curr1 = next1;
                mergeCurr = mergeCurr.getNext();
            }
            if (curr1 == null) {
                break;
            }
            if (curr1.getId() > curr2.getId()) {
                mergeCurr.setNext(curr2);
                curr2 = next2;
                mergeCurr = mergeCurr.getNext();
            }
        }
        if (curr1 != null) {
            mergeCurr.setNext(curr1);
        }
        if (curr2 != null) {
            mergeCurr.setNext(curr2);
        }
        return mergeList;
    }

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "aa");
        HeroNode node2 = new HeroNode(2, "bb");
        HeroNode node3 = new HeroNode(3, "cc");
        HeroNode node4 = new HeroNode(4, "dd");
//        SingleLinkedList list = new SingleLinkedList();
//        list.add(node1);
//        list.add(node3);
//        list.add(node2);
//        list.add(node4);
//        list.addByOrder(node1);
//        list.addByOrder(node3);
//        list.addByOrder(node2);
//        list.addByOrder(node2);
//        list.addByOrder(node4);
//        list.list();
//        HeroNode newNode3 = new HeroNode(4, "cccc");
//        list.update(newNode3);
//        list.list();
//        list.delete(1);
//        list.delete(4);
//        list.delete(3);
//        list.delete(2);
//        list.list();
//        System.out.println(list.getLength(list.getHead()));
//        System.out.println(list.findCountDownNode(list.getHead(), 3));
//        System.out.println("============");
//        list.reverse(list.getHead()).list();
//        list.reverseList(list.getHead());
//        list.list();
//        list.reversePrint(list.getHead());

        System.out.println("====================");
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        list1.add(node2);
        list1.add(node4);
        list2.add(node1);
        list2.add(node3);
        SingleLinkedList.merge(list1,list2).list();
    }
}

/**
 * 定义一个节点
 */
class HeroNode {
    private int id;
    private String name;
    private HeroNode next;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
