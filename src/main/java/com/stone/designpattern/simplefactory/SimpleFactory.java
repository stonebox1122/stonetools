package com.stone.designpattern.simplefactory;

public class SimpleFactory {
    // 方式1
    public static Car getCar(String type){
        if ("audi".equals(type)){
            return new Audi();
        } else if ("benz".equals(type)){
            return new Benz();
        } else {
            return null;
        }
    }

    // 方式2
    public static Car getAudi(){
        return new Audi();
    }

    public static Car getBenz(){
        return new Benz();
    }
}
