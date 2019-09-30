package com.stone.designpattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Date;

/**
 * 覆写Object的clone方法来实现对象的克隆
 * 此处是深克隆，即会克隆引用类型，通过序列化实现
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sheep3 implements Serializable {
    private String name;
    private Date birthday;

    public Sheep3 deepClone() throws IOException, ClassNotFoundException {
        // 序列化
        @Cleanup  ByteArrayOutputStream bos = new ByteArrayOutputStream();
        @Cleanup  ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Sheep3) ois.readObject();
    }
}