package com.stone.designpattern.strategy.improve;

public class PekingDuck extends Duck {
    public PekingDuck() {
        flyBehavior = new BadFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("~~北京鸭~~~");
    }
}
