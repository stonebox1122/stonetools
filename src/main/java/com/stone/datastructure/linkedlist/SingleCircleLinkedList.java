package com.stone.datastructure.linkedlist;

/**
 * @author stone
 * @date 2019/6/12 16:53
 * description 处理约瑟夫环问题
 */

class SingleCircleLinkedList {
    private Boy first = null;

    /**
     * 添加节点
     *
     * @param nums 节点个数
     */
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("参数错误，请输入大于1的整数。");
            return;
        }

        Boy currBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                currBoy = first;
            } else {
                currBoy.setNext(boy);
                boy.setNext(first);
                currBoy = boy;
            }
        }
    }

    /**
     * 遍历列表
     */
    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        Boy currBoy = first;
        while (true) {
            System.out.println(currBoy);
            currBoy = currBoy.getNext();
            if (currBoy == first) {
                break;
            }
        }
    }

    /**
     * 约瑟夫环
     *
     * @param nums    节点数量
     * @param startId 起始节点
     * @param count   计数个数
     */
    public void josephu(int nums, int startId, int count) {
        if (first == null || nums < 1 || startId < 1 || startId > nums || count <= 0) {
            System.out.println("输入参数错误，请重新输入");
            return;
        }

        // 定义一个helper指针，指向first前一个节点
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        // 移动first和helper到startId和startId前一个节点
        for (int i = 0; i < startId - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (helper != first) {
            // 进行报数，就是移动first和helper共count-1次
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            // 删除对应节点
            System.out.println(first.getId());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getId());
    }

    public static void main(String[] args) {
        SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList();
        singleCircleLinkedList.add(5);
        singleCircleLinkedList.show();
        singleCircleLinkedList.josephu(5,1,2);
    }
}

class Boy {
    private int id;
    private Boy next;

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                '}';
    }
}
