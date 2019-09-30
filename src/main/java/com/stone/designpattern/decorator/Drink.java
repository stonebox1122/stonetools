package com.stone.designpattern.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Drink {
    public String des; // 描述
    private float price = 0.0f;

    //计算费用的抽象方法
    //子类来实现
    public abstract float cost();
}
