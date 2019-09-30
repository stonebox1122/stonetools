package com.stone.designpattern.decorator;

public class Soy extends Decorator {
    public Soy(Drink obj) {
        super(obj);
        setDes("豆浆");
        setPrice(1.0f);
    }
}
