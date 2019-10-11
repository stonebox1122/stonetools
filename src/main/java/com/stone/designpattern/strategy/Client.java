package com.stone.designpattern.strategy;

public class Client {
    public static void main(String[] args) {
        Duck toyDuck = new ToyDuck();
        toyDuck.fly();
    }
}
