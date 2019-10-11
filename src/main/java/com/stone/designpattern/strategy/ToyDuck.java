package com.stone.designpattern.strategy;

public class ToyDuck extends Duck {
    @Override
    public void display() {
        System.out.println("玩具鸭");
    }

    public void quack() {
        System.out.println("玩具鸭不能叫~~");
    }

    public void swim() {
        System.out.println("玩具鸭不会游泳~~");
    }

    public void fly() {
        System.out.println("玩具鸭不会飞翔~~~");
    }
}
