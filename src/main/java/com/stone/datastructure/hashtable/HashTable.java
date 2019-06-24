package com.stone.datastructure.hashtable;

import java.util.Scanner;

/**
 * @author stone
 * @date 2019/6/20 11:10
 * description 哈希表
 */
public class HashTable {
    private int size;
    private EmpLinkedList[] empLinkedLists;

    public HashTable(int size) {
        this.size = size;
        this.empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int index = hashFun(emp.getId());
        empLinkedLists[index].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void findEmpById(int id){
        int index = hashFun(id);
        Emp emp = empLinkedLists[index].findEmpById(id);
        if (emp == null) {
            System.out.println("没有id为"+id+"的员工");
        } else {
            System.out.println("找到id为"+id+"的员工"+emp);
        }
    }

    public void deleteEmpById(int id){
        int index = hashFun(id);
        boolean result = empLinkedLists[index].deleteEmpById(id);
        if (result) {
            System.out.println("删除id为"+id+"的员工");
        } else {
            System.out.println("没有id为"+id+"的员工");
        }
    }

    public int hashFun(int id) {
        return id % size;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("add:    添加用户");
            System.out.println("list:   显示用户");
            System.out.println("find:   查找用户");
            System.out.println("del:    删除用户");
            System.out.println("exit:   退出程序");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入编号");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入编号");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入编号");
                    id = scanner.nextInt();
                    hashTable.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class Emp {
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
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

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList {
    private Emp head;

    public EmpLinkedList() {
    }

    public EmpLinkedList(Emp head) {
        this.head = head;
    }

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp currEmp = head;
        while (currEmp.getNext() != null) {
            currEmp = currEmp.getNext();
        }
        currEmp.setNext(emp);
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }

        System.out.println("第" + (no + 1) + "链表为");
        Emp currEmp = head;
        while (currEmp != null) {
            System.out.print(currEmp);
            currEmp = currEmp.getNext();
        }
        System.out.println();
    }

    public Emp findEmpById(int id){
        if (head == null) {
            return null;
        }

        Emp currEmp = head;
        while (currEmp != null) {
            if (currEmp.getId() == id) {
                break;
            }
            currEmp = currEmp.getNext();
        }
        return currEmp;
    }

    public boolean deleteEmpById(int id){
        if (head == null) {
            return false;
        }

        if (head.getId() == id) {
            if (head.getNext() == null) {
                head = null;
            }else {
                head = head.getNext();
            }
            return true;
        }

        Emp currEmp = head;
        while (currEmp.getNext() != null) {
            if (currEmp.getNext().getId() == id) {
                currEmp.setNext(currEmp.getNext().getNext());
                return true;
            }
            currEmp = currEmp.getNext();
        }
        return false;
    }
}
