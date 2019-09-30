package com.stone.designpattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @author stone
 * @date 2019/9/23 13:11
 * description 测试单例的反射和反序列化
 */
public class SingletonClient {
    public static void main(String[] args) throws Exception {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true

        // 测试通过反射的方式直接调用私有构造器
//        Class<Singleton> clazz = (Class<Singleton>) Class.forName("com.stone.designpattern.singleton.Singleton");
//        Constructor<Singleton> c = clazz.getDeclaredConstructor(null);
//        c.setAccessible(true);
//        Singleton s3 = c.newInstance();
//        Singleton s4 = c.newInstance();
//        System.out.println(s3 == s4);  // false

        // 测试通过反序列化方式构造多个对象
        FileOutputStream fos = new FileOutputStream("d:/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);
        oos.close();
        fos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
        Singleton s3 = (Singleton) ois.readObject();
        System.out.println(s1 == s3); // false
    }

}
