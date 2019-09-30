package com.stone.designpattern.adapter.classadapter;

// 适配器类
public class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        int srcV = output220V();
        int destV = srcV / 44;
        return destV;
    }
}
