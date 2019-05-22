package com.stone.designpattern.singleton;
public class SingletonHunger {
	private static SingletonHunger instance = new SingletonHunger();
	private SingletonHunger() {};
	public static SingletonHunger getInstance() {
		return instance;
	}
}