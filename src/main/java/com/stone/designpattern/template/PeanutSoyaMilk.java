package com.stone.designpattern.template;

public class PeanutSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("添加花生");
    }
}
