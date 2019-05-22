package com.stone.designpattern.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {
        Car audi = new AudiFactory().createCar();
        Car benz = new BenzFactory().createCar();
        audi.run();
        benz.run();
    }
}
