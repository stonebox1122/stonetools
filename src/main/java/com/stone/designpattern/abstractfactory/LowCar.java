package com.stone.designpattern.abstractfactory;

public class LowCar implements CarFactory {
    @Override
    public LowEngine createEngine() {
        return new LowEngine();
    }

    @Override
    public LowTyre createTyre() {
        return new LowTyre();
    }
}
