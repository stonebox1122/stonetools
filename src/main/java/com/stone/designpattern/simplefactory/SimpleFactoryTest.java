package com.stone.designpattern.simplefactory;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 没有使用简单工厂
//        Car audi = new Audi();
//        Car benz = new Benz();
//        audi.run();
//        benz.run();

        // 使用简单工厂
        Car audi1 = new SimpleFactory().getAudi();
        Car benz1 = new SimpleFactory().getBenz();
        audi1.run();
        benz1.run();


    }

}
