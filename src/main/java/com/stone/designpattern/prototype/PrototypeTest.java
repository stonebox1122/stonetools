package com.stone.designpattern.prototype;

import java.util.Date;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep dolly = new Sheep("Dolly", new Date(1234564134L));
        Sheep dolly1 = (Sheep) dolly.clone();

        System.out.println(dolly);
        System.out.println(dolly.getName());
        System.out.println(dolly.getBirthday());

        System.out.println("====================");

        dolly1.setName("Lily");
        dolly.setBirthday(new Date(431532413L));
        System.out.println(dolly1);
        System.out.println(dolly1.getName());
        System.out.println(dolly1.getBirthday());
        System.out.println(dolly.getBirthday());
    }
}
