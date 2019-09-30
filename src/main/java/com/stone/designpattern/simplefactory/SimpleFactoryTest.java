package com.stone.designpattern.simplefactory;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 没有使用简单工厂
//        Car audi = new Audi();
//        Car benz = new Benz();
//        audi.run();
//        benz.run();

        // 使用简单工厂，方式1
        Car audi = SimpleFactory.getCar("audi");
        Car benz = SimpleFactory.getCar("benz");
        audi.run();
        benz.run();
        System.out.println("============");

        // 使用简单工厂，方式2
        Car audi1 = SimpleFactory.getAudi();
        Car benz1 = SimpleFactory.getBenz();
        audi1.run();
        benz1.run();
    }
}
