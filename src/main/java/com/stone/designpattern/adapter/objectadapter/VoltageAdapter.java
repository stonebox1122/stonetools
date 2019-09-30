package com.stone.designpattern.adapter.objectadapter;

// 适配器类
public class VoltageAdapter implements IVoltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int destV = 0;
        if (voltage220V != null){
            int srcV = voltage220V.output220V();
            System.out.println("使用对象适配器，进行适配~~");
            destV = srcV / 44;
            System.out.println("适配完成，输出的电压为=" + destV);
        }
        return destV;
    }
}
