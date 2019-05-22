package com.stone.designpattern.singleton;
public class SingletonLazyAndThreadSecure {
	private static SingletonLazyAndThreadSecure instance;
	private SingletonLazyAndThreadSecure() {};
	
	public static synchronized SingletonLazyAndThreadSecure getInstance() {
		if(instance == null) {
			instance = new SingletonLazyAndThreadSecure();
		}
		return instance;
	}
}