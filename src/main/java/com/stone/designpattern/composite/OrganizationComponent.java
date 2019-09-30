package com.stone.designpattern.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
// 抽象构建（component）
public abstract class OrganizationComponent {
    private String name; // 名字
    private String des; // 说明

    protected  void add(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    protected  void remove(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    //方法print, 做成抽象的, 子类都需要实现
    protected abstract void print();
}
