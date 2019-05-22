package com.stone.designpattern.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        HighCar highCar = new HighCar();
        highCar.createEngine().autoStart();
        highCar.createTyre().monitor();
    }
}
