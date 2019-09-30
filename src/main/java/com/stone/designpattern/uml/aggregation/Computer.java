package com.stone.designpattern.uml.aggregation;

/**
 * @author stone
 * @date 2019/9/20 15:13
 * description
 */
public class Computer {
    private Mouse mouse; //鼠标可以和computer分离
    private Monitor monitor;//显示器可以和Computer分离
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    public void setMoniter(Monitor monitor) {
        this.monitor = monitor;
    }
}
