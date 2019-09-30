package com.stone.designpattern.builder1.improve;

public class HighBuilding extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println("高楼地基50m");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌墙50cm");
    }

    @Override
    public void roofed() {
        System.out.println("高楼屋顶50cm");
    }
}
