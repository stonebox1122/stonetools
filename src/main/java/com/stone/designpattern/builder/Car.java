package com.stone.designpattern.builder;


public class Car {
    private Engine engine;
    private Tyre tyre;

    public Car() {
    }

    public Car(Engine engine, Tyre tyre) {
        this.engine = engine;
        this.tyre = tyre;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }
}
