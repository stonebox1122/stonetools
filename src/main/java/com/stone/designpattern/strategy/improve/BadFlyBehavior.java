package com.stone.designpattern.strategy.improve;

public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly bad");
    }
}
