package com.stone.designpattern.prototype;

import java.util.Date;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date(1234564134L);
        Sheep dolly = new Sheep("Dolly", date);
        System.out.println(dolly);
        System.out.println(dolly.getName());
        System.out.println(dolly.getBirthday());

        System.out.println("====================");

        Sheep dolly1 = (Sheep) dolly.clone();
        System.out.println(dolly1);
        System.out.println(dolly1.getName());
        System.out.println(dolly1.getBirthday());

        System.out.println("====================");

        // 由于是浅克隆，克隆的对象和被克隆的对象都指向到了该Date（）对象。所以两个对象的Date都会进行改变
        date.setTime(2345667878L);
        System.out.println(dolly.getBirthday());
        System.out.println(dolly1.getBirthday());
    }
}
