package com.stone.designpattern.abstractfactory;

public interface CarFactory {
    EngineFactory createEngine();
    TyreFactory createTyre();
}
