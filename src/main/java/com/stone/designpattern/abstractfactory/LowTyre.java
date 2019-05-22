package com.stone.designpattern.abstractfactory;

public class LowTyre implements TyreFactory {
    @Override
    public void monitor() {
        System.out.println("低端轮胎无监控胎压功能");
    }
}
