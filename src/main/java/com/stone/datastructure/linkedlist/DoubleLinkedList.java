package com.stone.datastructure.linkedlist;

/**
 * @author stone
 * @date 2019/6/12 15:04
 * description
 */
public class DoubleLinkedList {
    private PersonNode head = new PersonNode(0, "");

    public PersonNode getHead() {
        return head;
    }

    public void setHead(PersonNode head) {
        this.head = head;
    }

    /**
     * 遍历双向链表
     */
    public void list() {
        if (head.getNext() == null) {
            System.out.println("the List is empty");
            return;
        }

        PersonNode temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    /**
     * 添加节点（不考虑编号顺序）
     * 1.找到当前链表的最后节点
     * 2.将最后节点的next指向新节点
     *
     * @param node
     */
    public void add(PersonNode node) {
        PersonNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);
        node.setPre(temp);
    }

    /**
     * 添加节点（考虑编号顺序）
     *
     * @param node
     */
    public void addByOrder(PersonNode node) {
        PersonNode temp = head;
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
            node.setPre(temp);
            if (temp.getNext() != null) {
                temp.getNext().setPre(node);
            }
            temp.setNext(node);

        }
    }

    /**
     * 修改节点，需要编号相同
     *
     * @param node
     */
    public void update(PersonNode node) {
        if (head.getNext() == null) {
            System.out.println("the List is empty.");
            return;
        }

        PersonNode temp = head.getNext();
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
        if (head.getNext() == null) {
            System.out.println("the List is empty.");
            return;
        }

        PersonNode temp = head.getNext();
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getId() == id) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.getPre().setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPre(temp.getPre());
            }
        } else {
            System.out.println("不能删除，不存在编号：" + id);
        }
    }

    public static void main(String[] args) {
        PersonNode personNode1 = new PersonNode(1, "aaa");
        PersonNode personNode2 = new PersonNode(2, "bbb");
        PersonNode personNode3 = new PersonNode(3, "ccc");
        PersonNode personNode4 = new PersonNode(4, "ddd");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(personNode1);
        doubleLinkedList.add(personNode2);
        doubleLinkedList.add(personNode3);
        doubleLinkedList.add(personNode4);
        doubleLinkedList.list();
        PersonNode newPersonNode = new PersonNode(4, "ddddddd");
        doubleLinkedList.update(newPersonNode);
        doubleLinkedList.list();
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
        System.out.println("====================");
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(personNode4);
        list.addByOrder(personNode3);
        list.addByOrder(personNode2);
        list.addByOrder(personNode1);
        list.list();
    }

}


/**
 * 定义一个节点
 */
class PersonNode {
    private int id;
    private String name;
    private PersonNode next;
    private PersonNode pre;


    public PersonNode(int id, String name) {
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

    public PersonNode getNext() {
        return next;
    }

    public void setNext(PersonNode next) {
        this.next = next;
    }

    public PersonNode getPre() {
        return pre;
    }

    public void setPre(PersonNode pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

