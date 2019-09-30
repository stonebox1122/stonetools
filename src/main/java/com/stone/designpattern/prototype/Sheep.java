package com.stone.designpattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 覆写Object的clone方法来实现对象的克隆
 * 此处是浅克隆，即不会克隆引用类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sheep implements Cloneable {
    private String name;
    private Date birthday;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
