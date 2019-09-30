package com.stone.designpattern.builder1;

public class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("普通打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("普通屋顶");
    }
}
