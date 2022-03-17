package com.tophoon.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("./src/com/tophoon/config/config.xml");
		MyBean bean = context.getBean("mybean", MyBean.class);
		bean.sayHello();
	}
}
