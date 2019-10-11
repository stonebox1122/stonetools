package com.stone.designpattern.strategy.improve;

public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly good");
    }
}
