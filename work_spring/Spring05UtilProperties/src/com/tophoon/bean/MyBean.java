package com.tophoon.bean;

public class MyBean {
	private String greeting;
	
	public MyBean(String greeting) {
		this.greeting = greeting;
	}
	
	public void sayHello() {
		System.out.println(greeting + "~");
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
