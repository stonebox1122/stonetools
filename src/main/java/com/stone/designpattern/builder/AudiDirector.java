package com.stone.designpattern.builder;

public class AudiDirector implements CarDirector {
    private CarBuilder builder;

    public AudiDirector() {
    }

    public AudiDirector(CarBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Car directCar() {
        Engine engine = builder.buildEngine();
        Tyre tyre = builder.buildTyre();
        Car car = new Car(engine, tyre);
        return car;
    }
}
