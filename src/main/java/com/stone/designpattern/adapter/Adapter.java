package com.stone.designpattern.adapter;

/**
 * @author stone
 * @date 2019/5/23 17:16
 * description 通过组合的方式来实现适配器
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleReq() {
        adaptee.print();
    }
}
