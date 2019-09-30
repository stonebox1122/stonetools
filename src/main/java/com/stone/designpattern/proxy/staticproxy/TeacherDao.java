package com.stone.designpattern.proxy.staticproxy;

public class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师上课。。。");
    }
}
