package com.stone.designpattern.singleton;
public class SingletonLazyAndThreadUnsecure {
	private static SingletonLazyAndThreadUnsecure instance;
	private SingletonLazyAndThreadUnsecure() {};
	
	public static SingletonLazyAndThreadUnsecure getInstance() {
		if(instance == null) {
			instance = new SingletonLazyAndThreadUnsecure();
		}
		return instance;
	}
}
