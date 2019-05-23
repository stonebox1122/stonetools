package com.stone.designpattern.adapter;

/**
 * @author stone
 * @date 2019/5/23 17:16
 * description 通过继承的方式来实现适配器
 */
public class Adapter2 extends Adaptee implements Target {

    @Override
    public void handleReq() {
        super.print();
    }
}
