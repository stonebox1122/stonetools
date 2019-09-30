package com.stone.designpattern.template;

public class RedBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("添加红豆");
    }
}
