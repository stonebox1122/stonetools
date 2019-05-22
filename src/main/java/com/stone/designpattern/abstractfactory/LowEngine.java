package com.stone.designpattern.abstractfactory;

public class LowEngine implements EngineFactory {
    @Override
    public void autoStart() {
        System.out.println("低端发动机没有自动启停功能");
    }
}
