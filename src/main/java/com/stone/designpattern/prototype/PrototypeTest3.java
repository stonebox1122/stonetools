package com.stone.designpattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class PrototypeTest3 {
    public static void main(String[] args) throws Exception {
        Sheep3 dolly = new Sheep3("Dolly", new Date(1234564134L));
        System.out.println(dolly);
        System.out.println(dolly.getName());
        System.out.println(dolly.getBirthday());

        System.out.println("====================");

        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(dolly);
        byte[] bytes = bos.toByteArray();

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Sheep3 dolly1 = (Sheep3) ois.readObject();

        dolly.setBirthday(new Date(1234546465L));
        System.out.println(dolly.getBirthday());

        System.out.println(dolly1);
        System.out.println(dolly1.getName());
        System.out.println(dolly1.getBirthday());
    }
}
