package com.stone.designpattern.builder;

public class Engine {
    private String name;

    public Engine() {
    }

    public Engine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
