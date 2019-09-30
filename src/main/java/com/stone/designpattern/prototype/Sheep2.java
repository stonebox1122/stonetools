package com.stone.designpattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 覆写Object的clone方法来实现对象的克隆
 * 此处是深克隆，即会克隆引用类型，通过在clone()方法中添加对引用类型的clone实现
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sheep2 implements Cloneable {
    private String name;
    private Date birthday;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Sheep2 sheep = (Sheep2) super.clone();
        sheep.birthday = (Date) this.birthday.clone();
        return sheep;
    }
}