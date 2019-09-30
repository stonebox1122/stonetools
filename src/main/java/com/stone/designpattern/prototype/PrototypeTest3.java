package com.stone.designpattern.prototype;

import java.util.Date;

public class PrototypeTest3 {
    public static void main(String[] args) throws Exception {
        Date date = new Date(1234564134L);
        Sheep3 dolly = new Sheep3("Dolly", date);
        System.out.println(dolly);
        System.out.println(dolly.getName());
        System.out.println(dolly.getBirthday());

        System.out.println("====================");

        Sheep3 dolly1 = (Sheep3) dolly.deepClone();
        System.out.println(dolly1);
        System.out.println(dolly1.getName());
        System.out.println(dolly1.getBirthday());

        System.out.println("====================");

        // 由于是深克隆，克隆的对象和被克隆的对象都指向了不同的Date（）对象，这里只会修改第一个对象的属性。
        date.setTime(2345667878L);
        System.out.println(dolly.getBirthday());
        System.out.println(dolly1.getBirthday());
    }
}
