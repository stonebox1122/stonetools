package com.stone.designpattern.abstractfactory;

public class HighTyre implements TyreFactory {
    @Override
    public void monitor() {
        System.out.println("高端轮胎有胎压监控功能");
    }
}
