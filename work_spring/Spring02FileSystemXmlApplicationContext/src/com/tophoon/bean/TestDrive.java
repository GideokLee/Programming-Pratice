package com.tophoon.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("./src/com/tophoon/config/context.xml"); // 파일 경로 기준
		MyBean bean = (MyBean) context.getBean("mybean");
		bean.sayHello();
	}
}
