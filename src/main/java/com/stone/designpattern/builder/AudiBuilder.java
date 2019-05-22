package com.stone.designpattern.builder;

public class AudiBuilder implements CarBuilder {
    @Override
    public Engine buildEngine() {
        System.out.println("创建引擎");
        return new Engine();
    }

    @Override
    public Tyre buildTyre() {
        System.out.println("创建轮胎");
        return new Tyre();
    }
}
