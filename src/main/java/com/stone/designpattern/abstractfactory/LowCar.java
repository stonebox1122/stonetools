package com.stone.designpattern.abstractfactory;

public class LowCar implements CarFactory {
    @Override
    public EngineFactory createEngine() {
        return new LowEngine();
    }

    @Override
    public TyreFactory createTyre() {
        return new LowTyre();
    }
}
