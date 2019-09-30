package com.stone.designpattern.iterator;

import java.util.Iterator;

public class ComputerCollegeIterator implements Iterator {
    //以数组方式存放数据
    Department[] departments;
    int position = 0; //遍历的位置

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if(position >= departments.length || departments[position] == null) {
            return false;
        }else {

            return true;
        }
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position += 1;
        return department;
    }

    @Override
    public void remove() {

    }
}
