package com.stone.designpattern.singleton;

import java.io.Serializable;

/**
 * @author stone
 * @date 2019/9/23 13:11
 * description 测试单例的反射和反序列化
 */
public class Singleton implements Serializable {
	private static Singleton instance;
	private Singleton() {
		if (instance != null) { // 解决反射问题
			throw new RuntimeException();
		}
	};
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	// 在反序列化时，直接调用readResolve()方法返回已有对象，而不需要单独再创建新对象
	private Object readResolve() {
		return instance;
	}
}
