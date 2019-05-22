package com.stone.designpattern.builder;

public class Tyre {
    private String name;

    public Tyre() {
    }

    public Tyre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
