package com.stone.designpattern.adapter;

/**
 * @author stone
 * @date 2019/5/23 17:14
 * description 需要使用适配器的对象，类似于只有USB接口的笔记本
 */
public class Client {
    public void test(Target t){
        t.handleReq();
    }

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        Client client = new Client();
        client.test(adapter);
    }
}
