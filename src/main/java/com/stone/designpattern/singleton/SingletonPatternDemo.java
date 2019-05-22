package com.stone.designpattern.singleton;
public class SingletonPatternDemo {	
	public static void main(String[] args) {
	     //不合法的构造函数
	     //编译时错误：构造函数 SingleObject() 是不可见的
	     //SingleObject singleObject = new SingleObject();		
		//获取唯一可用对象
		SingleObject singleObject = SingleObject.getInstance();
		//调用方法
		singleObject.showMessage();
		SingletonENUM.INSTANCE.showMessage();
	}
}