package com.stone.designpattern.simplefactory;

public class SimpleFactory {
    public  Car getAudi(){
        return new Audi();
    }
    public  Car getBenz(){
        return new Benz();
    }
}
