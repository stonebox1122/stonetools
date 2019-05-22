package com.stone.designpattern.prototype;

import java.io.Serializable;
import java.util.Date;

/**
 * 覆写Object的clone方法来实现对象的克隆
 * 此处是深克隆，通过序列化实现
 */
public class Sheep3 implements Serializable {
    private String name;
    private Date birthday;

    public Sheep3() {
    }

    public Sheep3(String name, Date birthday) {
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
}