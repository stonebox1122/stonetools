package com.stone.designpattern.prototype;

import java.util.Date;

/**
 * 覆写Object的clone方法来实现对象的克隆
 * 此处是浅克隆，即不会克隆对象里面的属性
 */
public class Sheep implements Cloneable {
    private String name;
    private Date birthday;

    public Sheep() {
    }

    public Sheep(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
