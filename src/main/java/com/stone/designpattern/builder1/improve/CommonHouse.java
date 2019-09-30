package com.stone.designpattern.builder1.improve;

public class CommonHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("普通地基5m");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通砌墙5cm");
    }

    @Override
    public void roofed() {
        System.out.println("普通屋顶5cm");
    }
}
