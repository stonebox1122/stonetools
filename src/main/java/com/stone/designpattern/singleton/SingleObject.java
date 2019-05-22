package com.stone.designpattern.singleton;
public class SingleObject {
	//创建SingleObject的一个对象
	private static SingleObject singleObject = new SingleObject();
	//私有构造函数，这样该类就不会实例化
	private SingleObject() {};
	//获取唯一可用对象
	public static SingleObject getInstance() {
		return singleObject;
	}
	public void showMessage() {
		System.out.println("Hello World");
	}
}
