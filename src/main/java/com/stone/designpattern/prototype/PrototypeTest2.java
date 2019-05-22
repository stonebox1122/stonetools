package com.stone.designpattern.prototype;

import java.util.Date;

public class PrototypeTest2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep2 dolly = new Sheep2("Dolly", new Date(1234564134L));
        Sheep2 dolly1 = (Sheep2) dolly.clone();
        dolly.setBirthday(new Date(341325341L));

        System.out.println(dolly);
        System.out.println(dolly.getName());
        System.out.println(dolly.getBirthday());

        System.out.println("====================");

        dolly1.setName("Lily");
        System.out.println(dolly1);
        System.out.println(dolly1.getName());
        System.out.println(dolly1.getBirthday());
    }
}
