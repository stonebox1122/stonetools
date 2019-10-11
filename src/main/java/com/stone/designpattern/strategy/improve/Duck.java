package com.stone.designpattern.strategy.improve;

public abstract class Duck {
    //属性, 策略接口
    FlyBehavior flyBehavior;

    public Duck() {}

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void display();//显示鸭子信息

    public void fly() {
        //改进
        if(flyBehavior != null) {
            flyBehavior.fly();
        }
    }
}
