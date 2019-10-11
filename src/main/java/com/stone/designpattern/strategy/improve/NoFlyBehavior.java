package com.stone.designpattern.strategy.improve;

public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("can not fly");
    }
}
